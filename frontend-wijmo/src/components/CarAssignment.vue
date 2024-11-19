<template>
    <div>
        <String label="RequesterName" v-model="value.requesterName" :editMode="editMode"/>
        <String label="Organization" v-model="value.organization" :editMode="editMode"/>
        <String label="EmployeeNumber" v-model="value.employeeNumber" :editMode="editMode"/>
        <String label="OfficeNumber" v-model="value.officeNumber" :editMode="editMode"/>
        <String label="MobileNumber" v-model="value.mobileNumber" :editMode="editMode"/>
        <Date label="RequestDate" v-model="value.requestDate" :editMode="editMode"/>
        <String label="ApproverInfo" v-model="value.approverInfo" :editMode="editMode"/>
        <String label="ApproverPosition" v-model="value.approverPosition" :editMode="editMode"/>
        <String label="UsagePurpose" v-model="value.usagePurpose" :editMode="editMode"/>
        <String label="NumberOfPassengers" v-model="value.numberOfPassengers" :editMode="editMode"/>
        <String label="OperationPeriod" v-model="value.operationPeriod" :editMode="editMode"/>
        <String label="RouteSetting" v-model="value.routeSetting" :editMode="editMode"/>
        <String label="Remarks" v-model="value.remarks" :editMode="editMode"/>
        <String label="PassengerContact" v-model="value.passengerContact" :editMode="editMode"/>
        <String label="AttachedDocuments" v-model="value.attachedDocuments" :editMode="editMode"/>
        <String label="CancellationReason" v-model="value.cancellationReason" :editMode="editMode"/>
        <UsageCategory offline label="UsageCategory" v-model="value.usageCategory" :editMode="editMode" @change="change"/>
        <CarType offline label="CarType" v-model="value.carType" :editMode="editMode" @change="change"/>
        <MainDepartment offline label="MainDepartment" v-model="value.mainDepartment" :editMode="editMode" @change="change"/>
        <OperationSection offline label="OperationSection" v-model="value.operationSection" :editMode="editMode" @change="change"/>
        <OperationType offline label="OperationType" v-model="value.operationType" :editMode="editMode" @change="change"/>
        <IncludeDriver offline label="IncludeDriver" v-model="value.includeDriver" :editMode="editMode" @change="change"/>
        <ProgressStage offline label="ProgressStage" v-model="value.progressStage" :editMode="editMode" @change="change"/>

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
    name: 'CarAssignment',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "carAssignments"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>

