$(
	function() {
		var page = $('#page').val();
		$('li[name="page_' + (parseInt(page) + 1) + '"]').addClass('active');
	}
);

$('a[name="page-link"]').click(
	function() {
		$('#form1 #page').val($(this).data('page'));
		$('#form1').submit();
	}
);


$('#btnQuery').click(
	function() {
		$('#form1 #page').val('');
		$('#form1').submit();
	}
);

function test(i) {
	$('#form1 #page').val('');
	$('#form1 #type').val(i);
	$('#form1').submit();
};