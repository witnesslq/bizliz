function greeter(person) {
    return "hello " + person;
}
var user = "jane 111aaafda1 af";
var people = [
    { name: 'ac', age: 1 },
    { name: 'bc', age: 2 }
];
var res = people.map(function (p) { return p.name + 'hello'; });
document.body.innerText = greeter(user);
//# sourceMappingURL=tster.js.map