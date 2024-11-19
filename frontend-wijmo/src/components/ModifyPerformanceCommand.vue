<template>

    <v-card outlined>
        <v-card-title>
            ModifyPerformance
        </v-card-title>

        <v-card-text>
            <String label="RegistrationId" v-model="value.registrationId" :editMode="editMode"/>
            <String label="VehicleNumber" v-model="value.vehicleNumber" :editMode="editMode"/>
            <date offline label="RegistrationDate" v-model="value.registrationDate" :editMode="editMode" @change="change"/>
            <String label="Purpose" v-model="value.purpose" :editMode="editMode"/>
            <String label="Departure" v-model="value.departure" :editMode="editMode"/>
            <String label="DepartureTime" v-model="value.departureTime" :editMode="editMode"/>
            <int offline label="AccumulatedDistanceBefore" v-model="value.accumulatedDistanceBefore" :editMode="editMode" @change="change"/>
            <String label="Destination" v-model="value.destination" :editMode="editMode"/>
            <String label="ArrivalTime" v-model="value.arrivalTime" :editMode="editMode"/>
            <int offline label="AccumulatedDistanceAfter" v-model="value.accumulatedDistanceAfter" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="modifyPerformance"
            >
                ModifyPerformance
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'ModifyPerformanceCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.registrationId = '';
            this.value.vehicleNumber = '';
            this.value.registrationDate = {};
            this.value.purpose = '';
            this.value.departure = '';
            this.value.departureTime = '';
            this.value.accumulatedDistanceBefore = {};
            this.value.destination = '';
            this.value.arrivalTime = '';
            this.value.accumulatedDistanceAfter = {};
        },
        watch: {
        },
        methods: {
            modifyPerformance() {
                this.$emit('modifyPerformance', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

