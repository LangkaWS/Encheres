/**
 * 
 */
window.onload = toggleVisibility();

function toggleVisibility() {
	if(user !== ""){
		let c = document.getElementsByClassName("connectedParams");
		for(let i = 0; i < c.length; i++) {
			c[i].style.display = "block";
		}
		toggleBuySell(buySell);
	}
}

function toggleBuySell(str) {
	if(str == "buy") {
		document.getElementById("listBuy").checked = true;
		toggleBuyButtons();
	} else if(str == "sell"){
		document.getElementById("listSell").checked = true;
		toggleSellButtons();
	}
}

function toggleBuyButtons() {
	enableLabel("buyFilterLabel");
	enableCheckbox("buyBox");
	
	disableLabel("sellFilterLabel");
	disableCheckbox("sellBox");
}

function toggleSellButtons() {
	enableLabel("sellFilterLabel");
	enableCheckbox("sellBox");
	
	disableLabel("buyFilterLabel");
	disableCheckbox("buyBox");
}

function disableCheckbox(className) {
	let arr = document.getElementsByClassName(className);
	for(let i = 0; i < arr.length; i++) {
		arr[i].checked = false;
		arr[i].disabled = true;
	}
}

function enableCheckbox(className) {
	let arr = document.getElementsByClassName(className);
	for(let i = 0; i < arr.length; i++) {
		arr[i].disabled = false;
		if(f1 == arr[i].id || f2 == arr[i].id || f3 == arr[i].id ) {
			arr[i].checked = true;
		}
	}
}

function enableLabel(className) {
	let list = document.getElementsByClassName(className);
	for(let i = 0; i < list.length; i++) {
		list[i].style.color = "black";
	}
}

function disableLabel(className) {
	let list = document.getElementsByClassName(className);
	for(let i = 0; i < list.length; i++) {
		list[i].style.color = "gray";
	}
}

