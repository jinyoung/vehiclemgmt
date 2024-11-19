<template>
    <div style="max-height:80vh;">
        <div class="hierarchy">
            <div>운행 &nbsp; ></div>
            <div>&nbsp; 운행 실적</div>
        </div>
        <div class="gs-bundle-of-buttons" style="max-height:10vh;">
            <v-btn @click="addNewRow" class="contrast-primary-text" small color="primary" :disabled="!hasRole('등록자')">
                <v-icon small>mdi-plus-circle-outline</v-icon>등록
            </v-btn>
            <v-btn  @click="editSelectedRow" class="contrast-primary-text" small color="primary" :disabled="!hasRole('등록자')">
                <v-icon small>mdi-pencil</v-icon>수정
            </v-btn>
            <v-btn @click="modifyPerformanceDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('등록자')">
                <v-icon small>mdi-minus-circle-outline</v-icon>운행 실적 수정
            </v-btn>
            <v-dialog v-model="modifyPerformanceDialog" width="500">
                <ModifyPerformanceCommand
                    @closeDialog="modifyPerformanceDialog = false"
                    @modifyPerformance="modifyPerformance"
                ></ModifyPerformanceCommand>
            </v-dialog>
            <v-btn @click="deleteSelectedRows" class="contrast-primary-text" small color="primary" :disabled="!hasRole('등록자')">
                <v-icon small>mdi-minus-circle-outline</v-icon>삭제
            </v-btn>
            <excel-export-button class="contrast-primary-text" :exportService="this.exportService" :getFlex="getFlex" />
        </div>


        <!-- the grid -->
        <wj-flex-grid
            ref="flexGrid"
            :key="value.length"
            :autoGenerateColumns="false"
            :allowAddNew="false"
            :allowDelete="true"
            :allowPinning="'SingleColumn'"
            :newRowAtTop="false"
            :showMarquee="true"
            :selectionMode="'MultiRange'"
            :validateEdits="false"
            :itemsSource="value"
            :initialized="flexInitialized"
            :selectionChanged="onSelectionChanged"
            style="margin-top:10px; max-height:65vh;"
            class="wj-felx-grid"
        >
            <wj-flex-grid-filter :filterColumns="['RowHeader','vehicleNumber','registrationDate','purpose','departure','departureTime','accumulatedDistanceBefore','destination','arrivalTime','accumulatedDistanceAfter','drivingDistance','purpose',]" />
            <wj-flex-grid-cell-template cellType="RowHeader" v-slot="cell">{{cell.row.index + 1}}</wj-flex-grid-cell-template>
            <wj-flex-grid-column binding="vehicleNumber" header="차량번호" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="registrationDate" header="등록일자" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="purpose" header="운행목적" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="departure" header="출발지" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="departureTime" header="출발시간" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="accumulatedDistanceBefore" header="운행전 누적 km" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="destination" header="도착지" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="arrivalTime" header="도착시간" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="accumulatedDistanceAfter" header="운행 후 누적 km" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="drivingDistance" header="DrivingDistance" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="purpose" header="Purpose" width="2*" :isReadOnly="true" align="center" />
        </wj-flex-grid>
        <v-col>
            <v-dialog
                v-model="openDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <template>
                    <v-card>
                        <v-toolbar
                            color="primary"
                            class="elevation-0"
                            height="50px"
                        >
                            <div style="color:white; font-size:17px; font-weight:700;">운행 실적 등록</div>
                            <v-spacer></v-spacer>
                            <v-icon
                                color="white"
                                small
                                @click="closeDialog()"
                            >mdi-close</v-icon>
                        </v-toolbar>
                        <v-card-text>
                            <VehiclePerformance :offline="offline"
                                :isNew="!itemToEdit"
                                :editMode="true"
                                v-model="newValue"
                                @add="append"
                                @edit="edit"
                            />
                        </v-card-text>
                    </v-card>
                </template>
            </v-dialog>
        </v-col>
        <v-col style="margin-bottom:40px;">
            <div class="text-center">
                <v-dialog
                    width="332.5"
                    fullscreen
                    hide-overlay
                    transition="dialog-bottom-transition"
                >
                    <v-btn
                        style="position:absolute; top:2%; right:2%"
                        @click="closeDialog()"
                        depressed
                        icon 
                        absolute
                    >
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                </v-dialog>
            </div>
        </v-col>
    </div>
</template>

<script>
import BaseGrid from '../base-ui/BaseGrid'

export default {
    name: 'vehiclePerformanceGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'vehiclePerformances',
        modifyPerformanceDialog: false,
    }),
    watch: {
    },
    methods:{
        modifyPerformance(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "modifyperformance", params)
                this.$EventBus.$emit('show-success','ModifyPerformance 성공적으로 처리되었습니다.')
                this.modifyPerformanceDialog = false
            }catch(e){
                this.$EventBus.$emit('show-error', e);
            }
            
        },
    }
}
</script>