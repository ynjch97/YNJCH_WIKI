function tabAction() { // 수업, 동아리 탭
    var tabWrap = $('.class-group-wrap'),
         tabBtn = tabWrap.find(".tab-wrap>.tab-title"),
         tabCont = $('.tab-cont-wrap');

    tabBtn.on('click', function(e) {
        e.preventDefault();
        var _this = $(this).parent('li'),
             index = _this.index();
        _this.addClass('on').siblings().removeClass('on');
        _this.next('.tab-cont-wrap').removeClass('on').addClass('on');
    });
}

function alrimToggle() { // 공지알림 레이어
	var mainAlrimLayer = $('.notice-layer');

	$('.btn-alrim-layer').on('click', function(e) {
		$(this).toggleClass('on');
		if ($(this).hasClass('on')){
			$(this).attr('title','공지알림 닫기');
		} else {
			$(this).attr('title','공지알림 펼쳐보기');
		}
	});
	$('.btn-alrim-layer').on('click', function(e) {
		if (mainAlrimLayer.hasClass('open')){
			mainAlrimLayer.removeClass('open');
		} else {
			mainAlrimLayer.addClass('open');
		}
	});
}

$(document).ready(function(){
    tabAction();
	alrimToggle();
});