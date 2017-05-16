function f() {
	return 1;
};

print(f() + 151);

function iterator() {
	for (var i = 0; i < 10; i++) {
		print("key: " + i + " value: " + i * i);
	}
}

iterator();

function add(a,b){
	return a+b;
}