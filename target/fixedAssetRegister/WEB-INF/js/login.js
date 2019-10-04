$(function() {
	var userfield = $("input[name=username]");
	var passwordfield = $("input[name=password]");
	$('button[type="submit"]')
			.click(
					function(e) {
						e.preventDefault();
						// little validation just to check username
						if (userfield.val() != "" && passwordfield.val() != "") {
							// $("body").scrollTo("#output");
							$("#output")
									.addClass(
											"alert alert-success animated fadeInUp")
									.html(
											"Welcome back "
													+ "<span style='text-transform:uppercase'>"
													+ userfield.val()
													+ "</span>");
							 $("form[name='loginForm']").submit(); 
						/*$("#output").removeClass(' alert-danger');
							$("input").css({
								"height" : "0",
								"padding" : "0",
								"margin" : "0",
								"opacity" : "0"
							});*/
							// change button text
						/*	$('button[type="submit"]').html("continue")
									.removeClass("btn-info").addClass(
											"btn-default").click(function() {
										$("input").css({
											"height" : "auto",
											"padding" : "10px",
											"opacity" : "1"
										}).val("");
									});*/

							// show avatar
							$(".avatar")
									.css(
											{
												"background-image" : "url('http://api.randomuser.me/0.3.2/portraits/women/35.jpg')"
											});
						} else {
							// remove success mesage replaced with error message
							$("#output").removeClass(' alert alert-success');
							if(userfield.val() == "") {
								$("#output").removeClass('alert alert-danger animated fadeInUp');
								$("#output").addClass(
								"alert alert-danger animated fadeInUp")
								.html("sorry enter a username");
							} else if (passwordfield.val() == "") {
								$("#output").removeClass('alert alert-danger animated fadeInUp');
								$("#output").addClass(
								"alert alert-danger animated fadeInUp")
								.html("sorry enter a Password");
							} 
						
						}
						// console.log(textfield.val());

					});
});
