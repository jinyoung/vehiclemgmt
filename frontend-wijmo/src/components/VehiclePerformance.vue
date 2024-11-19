<template>
    <div>
        <String label="등록자 정보" v-model="value.registrationId" :editMode="editMode"/>
        <String label="차량번호" v-model="value.vehicleNumber" :editMode="editMode"/>
        <Date label="등록일자" v-model="value.registrationDate" :editMode="editMode"/>
        <String label="운행목적" v-model="value.purpose" :editMode="editMode"/>
        <String label="출발지" v-model="value.departure" :editMode="editMode"/>
        <String label="출발시간" v-model="value.departureTime" :editMode="editMode"/>
        <String label="도착지" v-model="value.destination" :editMode="editMode"/>
        <String label="도착시간" v-model="value.arrivalTime" :editMode="editMode"/>
        <Number offline label="운행전 누적 km" v-model="value.accumulatedDistanceBefore" :editMode="editMode" @change="change"/>
        <Number offline label="운행 후 누적 km" v-model="value.accumulatedDistanceAfter" :editMode="editMode" @change="change"/>

        <v-divider class="border-opacity-100 my-divider"></v-divider>
        <v-layout row justify-end>
            <v-btn
                width="64px"
                color="primary"
                @click="save"
            >
                저장
            </v-btn>
        </v-layout>
    </div>
</template>


<script>

import BaseEntity from './base-ui/BaseEntity'

export default {
    name: 'VehiclePerformance',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "vehiclePerformances"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>

