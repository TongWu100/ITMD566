   


function skip(page){
	switch (page) {
	case 1:
		$('.J_iframe').attr('src','car_list.jsp');
		break;
	case 2:
		$('.J_iframe').attr('src','car_insert2.jsp');
		break;
	case 3:
		$('.J_iframe').attr('src','user_list.jsp');
		break;
	case 4:
		$('.J_iframe').attr('src','user_insert.jsp');
		break;
	case 5:
		$('.J_iframe').attr('src','carpart_list.jsp');
		break;
	case 6:
		$('.J_iframe').attr('src','carpart_insert2.jsp');
		break;
	case 7:
		$('.J_iframe').attr('src','account_list.jsp');
		break;
	case 8:
		$('.J_iframe').attr('src','account_list2.jsp');
		break;
	case 9:
		$('.J_iframe').attr('src','buy_list.jsp');
		break;
	case 10:
		$('.J_iframe').attr('src','user_list2.jsp');
		break;
	case 11:
		$('.J_iframe').attr('src','sc_notice_insert.jsp');
		break;
	case 12:
		$('.J_iframe').attr('src','sc_notice_select.jsp');
		break;
	case 13:
		$('.J_iframe').attr('src','mail.jsp');
		break;
	case 14:
		$('.J_iframe').attr('src','power.jsp');
		break;
	case 15:
		$('.J_iframe').attr('src','edition.jsp');
		break;
	case 16:
		$('.J_iframe').attr('src','sch_score.jsp');
		break;
	case 17:
		$('.J_iframe').attr('src','coa_score.jsp');
		break;
	
	case "gohome":
		$('.J_iframe').attr('src','home.jsp');
		break;
	default:
		break;
	}
	
};