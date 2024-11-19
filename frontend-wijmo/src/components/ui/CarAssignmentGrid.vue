<template>
    <div style="max-height:80vh;">
        <div class="hierarchy">
            <div>배차 &nbsp; ></div>
            <div>&nbsp; CarAssignment</div>
        </div>
        <div class="gs-bundle-of-buttons" style="max-height:10vh;">
            <v-btn @click="addNewRow" class="contrast-primary-text" small color="primary" :disabled="!hasRole('')">
                <v-icon small>mdi-plus-circle-outline</v-icon>등록
            </v-btn>
            <v-btn  @click="editSelectedRow" class="contrast-primary-text" small color="primary" :disabled="!hasRole('')">
                <v-icon small>mdi-pencil</v-icon>수정
            </v-btn>
            <v-btn @click="cancelCarAssignmentDialog = true" class="contrast-primary-text" small color="primary" >
                <v-icon small>mdi-minus-circle-outline</v-icon>CancelCarAssignment
            </v-btn>
            <v-dialog v-model="cancelCarAssignmentDialog" width="500">
                <CancelCarAssignmentCommand
                    @closeDialog="cancelCarAssignmentDialog = false"
                    @cancelCarAssignment="cancelCarAssignment"
                ></CancelCarAssignmentCommand>
            </v-dialog>
            <v-btn @click="deleteSelectedRows" class="contrast-primary-text" small color="primary" :disabled="!hasRole('')">
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
            <wj-flex-grid-filter :filterColumns="['RowHeader','requesterName','organization','employeeNumber','officeNumber','mobileNumber','requestDate','approverInfo','approverPosition','usagePurpose','numberOfPassengers','operationPeriod','routeSetting','remarks','passengerContact','attachedDocuments','cancellationReason','usageCategory','carType','mainDepartment','operationSection','operationType','includeDriver','progressStage',]" />
            <wj-flex-grid-cell-template cellType="RowHeader" v-slot="cell">{{cell.row.index + 1}}</wj-flex-grid-cell-template>
            <wj-flex-grid-column binding="requesterName" header="RequesterName" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="organization" header="Organization" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="employeeNumber" header="EmployeeNumber" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="officeNumber" header="OfficeNumber" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="mobileNumber" header="MobileNumber" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="requestDate" header="RequestDate" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="approverInfo" header="ApproverInfo" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="approverPosition" header="ApproverPosition" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="usagePurpose" header="UsagePurpose" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="numberOfPassengers" header="NumberOfPassengers" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="operationPeriod" header="OperationPeriod" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="routeSetting" header="RouteSetting" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="remarks" header="Remarks" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="passengerContact" header="PassengerContact" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="attachedDocuments" header="AttachedDocuments" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="cancellationReason" header="CancellationReason" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="usageCategory" header="UsageCategory" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="carType" header="CarType" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="mainDepartment" header="MainDepartment" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="operationSection" header="OperationSection" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="operationType" header="OperationType" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="includeDriver" header="IncludeDriver" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="progressStage" header="ProgressStage" width="2*" :isReadOnly="true" align="center" />
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
                            <div style="color:white; font-size:17px; font-weight:700;">CarAssignment 등록</div>
                            <v-spacer></v-spacer>
                            <v-icon
                                color="white"
                                small
                                @click="closeDialog()"
                            >mdi-close</v-icon>
                        </v-toolbar>
                        <v-card-text>
                            <CarAssignment :offline="offline"
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
    name: 'carAssignmentGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'carAssignments',
        cancelCarAssignmentDialog: false,
    }),
    watch: {
    },
    methods:{
        cancelCarAssignment(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "", params)
                this.$EventBus.$emit('show-success','CancelCarAssignment 성공적으로 처리되었습니다.')
                this.cancelCarAssignmentDialog = false
            }catch(e){
                this.$EventBus.$emit('show-error', e);
            }
            
        },
    }
}
</script>