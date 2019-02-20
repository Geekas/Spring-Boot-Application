function allFunctions() {
	$('li.active').removeClass('active');
	$('a[href="' + location.pathname + '"]').closest('li').addClass('active');
	$("input").click(function() {
		$(this).next().show();
		$(this).next().hide();
	});
	$('#emplTable').DataTable();
	$('#projTable').DataTable();
	$('#mngEmplTable').DataTable();
	$('#mngProjTable').DataTable();
	$('.no_name').removeClass('sorting');
	$("th").click(function() {
		$('.no_name').removeClass('sorting sorting_asc sorting_desc');
	});
	$('#workStart').datepicker({
		dateFormat : 'yy/mm/dd'
	});
}