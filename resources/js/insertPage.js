  var pointage=document.getElementsByName("age");
  var pointsex=document.getElementsByName("gender");
  var pointvaccine=document.getElementsByName("Vaccine");
  var checks=0;
  var checka=0;
  var checkv=0;
  var confirm_data;
  function value_check()
  {
                         
    for (var i=0;i<pointage.length;i++){
      if(pointage[i].checked===true){
        checka=checka+1;
      }
    }
    for (var j=0;j<pointsex.length;j++){
      if(pointsex[j].checked===true){
        checks=checks+1;
      }
    }
    for (var k=0;k<pointvaccine.length;k++){
      if(pointvaccine[k].checked===true){
        checkv=checkv+1;
      }
    }
    
    if(checka===0 || checks===0 || checkv===0)
      {
        alert("입력값이 누락되었습니다.");
		return false;
      }
	else
	{
		return true;
	}
  }
  function delete_form(){
       alert("데이터가 삭제되었습니다.");
  }
