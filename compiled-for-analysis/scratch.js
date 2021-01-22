// Compiled by ClojureScript 1.10.773 {}
goog.provide('seven_guis.scratch');
goog.require('cljs.core');
seven_guis.scratch.stime = performance.now();
cljs.core.print.call(null,(function (){var text = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa ";
var range = (new cljs.core.List(null,cljs.core.range.call(null,(10000)),null,(1),null));
return cljs.core.reduce.call(null,(function (x1,x2){
return (x1 + (function (){var i = (0);
while(true){
if((((i >= x2)) || ((i >= ((text).length))) || (cljs.core.not_EQ_.call(null,cljs.core.nth.call(null,text,i),"a")))){
return i;
} else {
var G__57551 = (i + (1));
i = G__57551;
continue;
}
break;
}
})());
}),(0),range);
})());
console.log((performance.now() - seven_guis.scratch.stime));
seven_guis.scratch.parse_lisp = (function seven_guis$scratch$parse_lisp(text){
var word_regex = /^ ?[^\(\) ] ?/;
var text_left = text;
var tree = cljs.core.PersistentVector.EMPTY;
var idx_stack = cljs.core.PersistentVector.EMPTY;
while(true){
var match = cljs.core.re_matches.call(null,word_regex,text);
return null;
break;
}
});
