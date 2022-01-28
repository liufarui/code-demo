var id = setInterval(timeLimit, 10);
//window.id;
var stop = '0';
function timeLimit() {
    var refreshHours = new Date().getHours();
    var refreshMin = new Date().getMinutes();
    var refreshSec = new Date().getSeconds();
    //    if(stop == '0' && refreshHours=='9' && refreshMin=='0' && refreshSec == '0'){
    //      stop = '1';
    meeting();
    //      clearInterval(id);
    //    }`
}

function meeting() {
    var meetingMap = new Map([['清华园', '2001005954'], ['未名湖', '2001005955'], ['明德楼', '2001005956'], ['卢沟桥', '2001006013'], ['五亭桥', '2001006015'], ['罗湖桥', '2001006073'], ['十七孔桥', '2001006081'], ['钱塘江桥', '2001006138'], ['龙脑桥', '2001006143'], ['东关桥', '2001006144'], ['泗溪东桥', '2001006128'], ['彩虹桥', '2001006129'], ['程阳桥', '2001006130'], ['双龙桥', '2001006131'], ['鱼沼飞梁', '2001006164'], ['万宁桥', '2001006165'], ['广宁桥', '2001006166'], ['杭州湾桥', '2001005989'], ['七凤桥', '2001006028'], ['清江桥', '2001006029'], ['小犬座', '2001006221'], ['小熊座', '2001006201'], ['大熊座', '2001006202'], ['天龙座', '2001006203'], ['天鹅座', '2001006206'], ['天箭座', '2001006209'], ['蝎虎座', '2001006212'], ['小狮座', '2001006215'], ['猎犬座', '2001006216'], ['牧夫座', '2001006218'], ['人马座', '2001006219'], ['御夫座', '2001006220'], ['三角座', '2001006222'], ['仙王座', '2001006223'], ['仙后座', '2001006224'], ['仙女座', '2001006227'], ['罗盘座', '2001006036'], ['宝瓶座', '2001005991'], ['波江座', '2001005992'], ['飞马座', '2001006958'], ['涵青亭', '2001006040'], ['木香亭', '2001006041'], ['乐游园', '2001006242'], ['杜甫草堂', '2001006236'], ['望江楼', '2001006237'], ['思贤园', '2001006239'], ['上林苑', '2001006240'], ['东苑', '2001006241'], ['清晖园', '2001006243'], ['沧浪亭', '2001006244'], ['避暑山庄', '2001006246'], ['留园', '2001006249'], ['瞻园', '2001006250'], ['集芳园', '2001006251'], ['拙政园', '2001006252'], ['寄畅园', '2001006253'], ['君子轩', '2001006254'], ['听雨轩', '2001006255'], ['绿满轩', '2001006257'], ['揖峰轩', '2001006258'], ['竹里轩', '2001006262'], ['栖霞亭', '2001006263'], ['怡秀亭', '2001006265'], ['杏花春', '2001006019'], ['五峰仙', '2001006020'], ['玉玲珑', '2001006021'], ['芙蓉榭', '2001006037'], ['湖光榭', '2001006038'], ['知园', '2001006301'], ['逸园', '2001006302'], ['未央宫', '2001006957'], ['青溪', '2001006170'], ['春思', '2001006171'], ['如梦令', '2001006172'], ['忆江南', '2001006173'], ['望仙楼', '2001006174'], ['彩云归', '2001006178'], ['送别', '2001006184'], ['红豆', '2001006186'], ['江雪', '2001006188'], ['乌衣巷', '2001006189'], ['静夜思', '2001006191'], ['春望', '2001006192'], ['竹里馆', '2001006193'], ['寒食', '2001006194'], ['琵琶行', '2001006197'], ['长干行', '2001006198'], ['秋叶曲', '2001006032'], ['桃源行', '2001006033']]);

    var url = "http://jmrs.jd.com/meetingOrder/create";

    /*
 *	改为会议当天时间
 */
    var date = "2021-08-03";

    /*
     *	改为自己名字
     *	可以随便填，此处为会议主题
     */
    var name = "liufarui预约了";

    /*
     *	meetingName：会议室中文名字
     *	meetingEstimateStime：会议开始时间
     *	meetingEstimateEtime：会议结束时间
     *	时间为正数，间隔最大一小时
     *	多个会议室多个时间可以继续添加，但最多只会预约成功3个
     */
    var meetingInfos =
        [
            {
                "meetingName": "清华园",
                "meetingEstimateStime": 1200,
                "meetingEstimateEtime": 1300
            }
                ,
                {
                    "meetingName":"未名湖",
                    "meetingEstimateStime":1330,
                    "meetingEstimateEtime":1400
                }
//                ,
            //    {
            //        "meetingName":"清华园",
            //        "meetingEstimateStime":1700,
            //        "meetingEstimateEtime":1800
            //    }
        ];
    for (var i in meetingInfos) {
        var params = { "meetingName": "orz", "meetingCode": "orz", "workplaceCode": "1001000530", "districtCode": "13", "meetingEstimateDate": "orz", "meetingEstimateStime": 1330, "meetingEstimateEtime": 1430, "bookJoyMeeting": 0, "joyMeetingParam    ": { "meeting": { "password": "" } }, "meetingSubject": "orz", "lang": "zh" };
        params.meetingName = meetingInfos[i].meetingName;
        params.meetingCode = meetingMap.get(meetingInfos[i].meetingName);
        params.meetingEstimateDate = date;
        params.meetingEstimateStime = meetingInfos[i].meetingEstimateStime;
        params.meetingEstimateEtime = meetingInfos[i].meetingEstimateEtime;
        params.meetingSubject = name + meetingInfos[i].meetingName;
        console.log(params);
//        var XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;
        var xhr = new XMLHttpRequest();
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onload = function (e) {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    console.log(xhr.responseText);
                } else {
                    console.error(xhr.statusText);
                }
            }
        };
        xhr.onerror = function (e) {
            console.error(xhr.statusText);
        };
        xhr.send(JSON.stringify(params));
    }
}
