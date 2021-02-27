function yearb() {
  var targetyb = document.getElementById("yearb");
  var yearn1 = targetyb.options[targetyb.selectedIndex].value;
  return yearn1;
}
function monthb() {
  var targetmb = document.getElementById("monthb");
  var monthn1 = targetmb.options[targetmb.selectedIndex].value;
  return monthn1;
}
function dayb() {
  var targetdb = document.getElementById("dayb");
  var dayn1 = targetdb.options[targetdb.selectedIndex].value;
  return dayn1;
}

function yeara() {
  var targetya = document.getElementById("yeara");
  var yearn2 = targetya.options[targetya.selectedIndex].value;
  return yearn2;
}
function montha() {
  var targetma = document.getElementById("montha");
  var monthn2 = targetma.options[targetma.selectedIndex].value;
  return monthn2;
}
function daya() {
  var targetda = document.getElementById("daya");
  var dayn2 = targetda.options[targetda.selectedIndex].value;
  return dayn2;
}

function datevalidation() {
  var year1 = yearb();
  var month1 = monthb();
  var day1 = dayb();
  var year2 = yeara();
  var month2 = montha();
  var day2 = daya();
  var datetime1 = new Date(year1, month1, day1, 10, 40, 55);
  var datetime2 = new Date(year2, month2, day2, 10, 40, 55);
  if (datetime1.getTime() >= datetime2.getTime()) {
    alert("날짜 입력이 잘못되었습니다.");
  }
}

  function delete_form(){
       alert("데이터가 삭제되었습니다.");
  }
