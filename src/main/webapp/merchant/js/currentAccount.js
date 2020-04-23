$(function () {
    $('#daterangepicker').daterangepicker({
        "autoUpdateInput": false,
        //maxDate:new Date(),
        "timePicker": true,
        "timePicker24Hour": true,
        "timePickerSeconds": true,
        "locale": {
            "format": "YYYY-MM-DD HH:mm:ss",
            "separator": " / ",
            "applyLabel": "选择",
            "cancelLabel": "取消",
            "fromLabel": "From",
            "toLabel": "To",
            "customRangeLabel": "Custom",
            "daysOfWeek": [
                "日",
                "一",
                "二",
                "三",
                "四",
                "五",
                "六"
            ],
            "monthNames": [
                "1月",
                "2月",
                "3月",
                "4月",
                "5月",
                "6月",
                "7月",
                "8月",
                "9月",
                "10月",
                "11月",
                "12月"
            ],
            "firstDay": 1
        }
    }, function(start, end, label) {
        $('#daterangepicker').val(start.format('YYYY-MM-DD HH:mm:ss') + ' / ' + end.format('YYYY-MM-DD HH:mm:ss'));
        //console.log("New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')");
    });
})


function submit($type)
{
    if('export' == $type){
        $('#task_type').val('export');
    } else {
        $('#task_type').val('search');
    }

    $('#search-form').submit();
}