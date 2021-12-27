var myfunct = function () {
	console.log('myfunct');
	$('.js-check-all').on('click', function () {

		if ($(this).prop('checked')) {
			$('.control--checkbox input[type="checkbox"]').each(function () {
				$(this).prop('checked', true);
			});
		} else {
			$('.control--checkbox input[type="checkbox"]').each(function () {
				$(this).prop('checked', false);
			});
		}

	});
	var check = function () {
		$('.ios-switch input[type="checkbox"]').each(function () {
			$(this).prop('checked', true);
			$(this).closest('tr').removeClass('active');
		});
	};
	var uncheck = function () {
		$('.ios-switch input[type="checkbox"]').each(function () {
			$(this).prop('checked', false);
			$(this).closest('tr').addClass('active');
		});
	};
	$('.js-ios-switch-all').on('change', function () {
		console.log('helo switch');
		if ($(this).prop('checked')) {
			check();
		} else {
			uncheck();
		}

	});



};
$(document).ready(function () {
//    myfunct();
	$('#form-cate').on('submit', function (e) {
		e.preventDefault();
		var categoryname = $('#newCategoryname').val();
		$.ajax({
			type: 'POST',
			enctype: 'multipart/form-data',
			data: new FormData(this),
			url: 'Settings?categoryname=' + categoryname,
			success: function (result) {
				if (result === 'Fail') {
					alert('Insert fail ! please try again ');
				} else {
					$('#addCategory').css("display", "none");
					var tb = $('#cateTb').find('tbody');
					tb.empty();
					tb.append(result);
					console.log('success');
				}
			},
			contentType: false,
			cache: false,
			processData: false
		});
	});



	$(document).on('click', '.ios-switch input[type="checkbox"]', function () {
		console.log('ok');
		if ($(this).closest('tr').hasClass('active')) {
			$(this).closest('tr').removeClass('active');
		} else {
			$(this).closest('tr').addClass('active');
		}
		var cn = $(this).closest('tr').find('a').html();
		var st;
		if ($(this).prop('checked')) {
			console.log('ok');
			st = '1';
		} else {
			st = '0';
		}
		$.ajax({
			type: 'POST',
			data: {action: 'c', cn: cn, st: st},
			url: 'CheckUsername',
			success: function (result) {
				$(".alert").fadeIn(100);
				window.setTimeout(function () {
					$(".alert").fadeOut(1000);
				}, 1000);
			}
		});
	});
	var check = function () {
		$('.ios-switch input[type="checkbox"]').each(function () {
			$(this).prop('checked', true);
			$(this).closest('tr').removeClass('active');
		});
	};
	var uncheck = function () {
		$('.ios-switch input[type="checkbox"]').each(function () {
			$(this).prop('checked', false);
			$(this).closest('tr').addClass('active');
		});
	};
	$('.js-ios-switch-all').on('click', function () {

		if ($(this).prop('checked')) {
			check();
		}
		if (!$(this).prop('checked')) {
			uncheck();
		}

	});
});





