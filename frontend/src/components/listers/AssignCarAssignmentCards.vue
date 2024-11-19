<template>
    <div>
        <v-card
            class="mx-auto"
            outlined
            color="primary"
            style="padding:10px 0px 10px 0px; margin-bottom:40px;"
        >
            <v-row>
                <v-list-item class="d-flex" style="background-color: white;">
                    <h1 class="align-self-center ml-3">CarAssignment</h1>
                    <div class="secondary-text-color" style="margin-left:30px;">차를 assign 받기 위해 &#x27;배차신청&#x27; 화면과 assign 받은 차량을 조회하는 &#x27;신청현황&#x27; 화면을 각각 만들려고 해.

&#x27;배차신청&#x27; 화면에는 등록자 정보를 조회하여 보여주는 테이블과 신청내용을 작성하는 테이블로 구분되어 있어. 등록자 정보 테이블은 등록자 이름, 소속(org), 직번(employee number), 전화번호(office number), 휴대전화(mobile number), 신청일자(YYYY.MM.DD), 결재자 정보, 결재자 직책이 조회 됨. 조회된 결재자 정보는 돋보기 버튼을 눌러 다른 사람을 검색하고 선택할 수 있어야 해. 돋보기 버튼을 누르면 팝업이 뜨는 형태야. 신청내용 테이블에는 이름, 사용구분, 사용목적, 운행구간 설정, 주관부서, 승차인원, 차종, 운행기간, 운전자 포함여부, 운행구간 설정, 비고, 탑승자 연락처, 첨부문서 칸으로 구성되어 있어. 이름은 직원이름을 검색할 수 있어야 해. 돋보기 버튼을 누르면 다른 사람을 검색하고 선택할 수 있어야 해. 사용구분은 업무지원, 대외활동 값을 dropdown menu에서 선택할 수 있어야 해. 사용목적은 text type으로 입력할 수 있어야 해. 운행구간 설정은 radio button으로 시내와 시외 중 선택할 수 있어야 해. 그리고, 편도와 왕복 중 dropdown menu로 선택할 수 있어야 해. 주관부서는 서울(포스코센터), 포항제철소, 광양제철소 중 dropdown menu로 선택할 수 있어야 해. 승차인원은 text type으로 입력할 수 있어야 해. 차종은 radio button으로 승용차, 승합차, 화물차 중 선택할 수 있어야 해. 운행기간은 from~to date를 YYYY.MM.DD 형식으로 캘린더에서 선택할 수 있어야 해. 운전자 포함여부는 radio button으로 YES와 NO 중 선택할 수 있어야 해. 운행구간 설정은 text type으로 입력할 수 있어야 하고, default 값으로 -출발지: 출발시간 포함작성 \n- 경유지: 도착시간 포함작성\n-도착지 : 도착시간 포함작성 이 입력되어 있어야 해. 비고는 text type으로 입력할 수 있어야 하고, default 값으로 -기타 요청사항 : 이 입력되어 있어야 해. 탑승자 연락처는 text type을 입력할 수 있어야 해. 첨부문서는 찾아보기 버튼을 눌렀을 시 API 호출이 가능해야 해. 비고 를 제외한 나머지 field는 required 한 항목으로 입력 값이 없으면 신청 버튼이 활성화 되지 않도록 해야해. 비고를 제외한 나머지 field 가 채워져 있으면 신청 버튼을 눌렀을 시 모든 값이 DB에 저장되어야 해.

신청현황 화면에는 등록자가 등록한 신청내용의 모든 기록이 조회되는 화면이야. 신청현황 화면에는 조회를 위한 필터로 조회구분, 진행단계가 있어. 조회구분은 결재일, 신청일, 운행일을 dropdown menu로 선택할 수 있고, from~to date를 선택할 수 있어. from~to date는 YYYY.MM.DD 형식이고, 캘린더 아이콘을 눌렀을 시 달력이 나타나고, 선택된 날짜가 from~to date로 선택되어 조회되는 구조야. 진행단계는 전체, 접수, 반려, 배차완료가 dropdown memu로 조회되어야 해. 선택된 조회 필터 값들을 조회 버튼을 눌러 조회하면 테이블 형태로 기존 신청내용이 조회가 되고, 테이블은 번호, 운행목적, 소속, 이름, 직번, 직능자격, 운행일, 신청일, 결재일, 진행단계 컬럼이 있어. 개별 신청 내용은 팝업 형태로 조회될 수 있어야 하고, 조회된 팝업화면에는 수정, 신청취소, 인쇄 버튼이 있어야 해. 수정 버튼을 누르면 진행단계 &#x27;접수&#x27;에서는 배차신청 내용이 수정될 수 있어야 해. 신청취소 버튼을 누르면 진행단계 &#x27;접수&#x27;에서 &#x27;배차취소&#x27; 단계로 변경되어야 해. 인쇄 버튼을 누르면 팝업 화면이 출력될 수 있어야 해.</div>
                </v-list-item>
            </v-row>
        </v-card>
        <v-col style="margin-bottom:40px;">
            <div class="text-center">
                <v-dialog
                        v-model="openDialog"
                        width="332.5"
                        fullscreen
                        hide-overlay
                        transition="dialog-bottom-transition"
                >
                    <AssignCarAssignment :offline="offline" class="video-card" :isNew="true" :editMode="true" v-model="newValue" 
                            @add="append" v-if="tick"/>

                    <v-btn
                            style="postition:absolute; top:2%; right:2%"
                            @click="closeDialog()"
                            depressed
                            icon 
                            absolute
                    >
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                </v-dialog>

                <v-row>
                    <v-card
                        class="mx-auto"
                        style="height:300px; width:300px; margin-bottom:20px; text-align: center;"
                        outlined
                    >
                        <v-list-item>
                            <v-list-item-avatar 
                                class="mx-auto"
                                size="80"
                                style="margin-top:80px;"
                            ><v-icon color="primary" x-large>mdi-plus</v-icon>
                            </v-list-item-avatar>
                        </v-list-item>

                        <v-card-actions>
                            <v-btn 
                                v-on="on"
                                class="mx-auto"
                                outlined
                                rounded
                                @click="openDialog=true;"
                                color="primary"
                                style="font-weight:500; font-size:20px; padding:15px; border:solid 2px; max-width:250px; overflow:hidden"
                            >
                                CarAssignment 등록
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-row>
            </div>
        </v-col>
        <v-row>
            <AssignCarAssignment :offline="offline" class="video-card" v-for="(value, index) in values" v-model="values[index]" v-bind:key="index" @delete="remove"/>
        </v-row>
    </div>
</template>

<script>

    const axios = require('axios').default;
    import AssignCarAssignment from './../AssignCarAssignment.vue';

    export default {
        name: 'AssignCarAssignmentManager',
        components: {
            AssignCarAssignment,
        },
        props: {
            offline: Boolean
        },
        data: () => ({
            values: [],
            newValue: {},
            tick : true,
            openDialog : false,
        }),
        async created() {
            var me = this;
            if(me.offline){
                if(!me.values) me.values = [];
                return;
            } 

            var temp = await axios.get(axios.fixUrl('/carAssignments'))
            me.values = temp.data._embedded.carAssignments;
            
            me.newValue = {
                'requesterName': '',
                'organization': '',
                'employeeNumber': '',
                'officeNumber': '',
                'mobileNumber': '',
                'requestDate': '2024-11-19',
                'approverInfo': '',
                'approverPosition': '',
                'usagePurpose': '',
                'numberOfPassengers': '',
                'operationPeriod': '',
                'routeSetting': '',
                'remarks': '',
                'passengerContact': '',
                'attachedDocuments': '',
                'cancellationReason': '',
                'usageCategory': '',
                'carType': '',
                'mainDepartment': '',
                'operationSection': '',
                'operationType': '',
                'includeDriver': '',
                'progressStage': '',
            }
        },
        methods:{
            closeDialog(){
                this.openDialog = false
            },
            append(value){
                this.tick = false
                this.newValue = {}
                this.values.push(value)
                
                this.$emit('input', this.values);

                this.$nextTick(function(){
                    this.tick=true
                })
            },
            remove(value){
                var where = -1;
                for(var i=0; i<this.values.length; i++){
                    if(this.values[i]._links.self.href == value._links.self.href){
                        where = i;
                        break;
                    }
                }

                if(where > -1){
                    this.values.splice(i, 1);
                    this.$emit('input', this.values);
                }
            },
        }
    };
</script>


<style>
    .video-card {
        width:300px; 
        margin-left:4.5%; 
        margin-top:50px; 
        margin-bottom:50px;
    }
</style>

