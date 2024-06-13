var rectangle = document.getElementById("rectangle");
var box = document.getElementById("infoBox");
var title = document.getElementById("rectangleName");
var button = document.getElementById("confirm");

var rName = document.getElementById("name");
var width = document.getElementById("width");
var height = document.getElementById("height");
var color = document.getElementById("color");

// Initial rectangle update
box.style.minWidth = String(Number(width.value) + 40) + "px";
title.innerHTML = rName.value; 
rectangle.style.width = width.value + "px";
rectangle.style.height = height.value + "px";
rectangle.style.backgroundColor = color.value;

// Each listener will update its corresponding element
rName.addEventListener('input', function() {
    title.innerHTML = rName.value;
});
width.addEventListener('input', function() {
    box.style.minWidth = String(Number(width.value) + 40) + "px";
    rectangle.style.width = width.value + "px";
});
height.addEventListener('input', function() {
    rectangle.style.height = height.value + "px";
});
color.addEventListener('input', function() {
    rectangle.style.backgroundColor = color.value;
});

// If any fields are empty when the user submits, fill with default values to avoid errors
button.addEventListener('click', function() {
    if (rName.value == '') rName.value = 'Rectangle';
    if (width.value == '') width.value = 100;
    if (height.value == '') height.value = 100;
})



