$(".viewgoodspack").click(function(){
	var m_id = $(this).attr('id');
	window.location.href="queryGoodsPackById.action?id="+m_id;
	
	
});