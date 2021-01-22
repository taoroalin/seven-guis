// Compiled by ClojureScript 1.10.773 {}
goog.provide('seven_guis.cells');
goog.require('cljs.core');
goog.require('reagent.core');
goog.require('clojure.string');
goog.require('cljs.pprint');
goog.require('seven_guis.examples');
goog.require('instaparse.core');
goog.require('clojure.walk');
cljs.core.print.call(null,(function (){var text = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa ";
var start__4626__auto__ = cljs.core.system_time.call(null);
var ret__4627__auto__ = cljs.core.repeatedly.call(null,(10000),(function (){
var i = (0);
while(true){
if((((i >= ((text).length))) || (cljs.core.not_EQ_.call(null,cljs.core.nth.call(null,text,i),"a")))){
return i;
} else {
var G__68302 = (i + (1));
i = G__68302;
continue;
}
break;
}
}));
cljs.core.prn.call(null,["Elapsed time: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1((cljs.core.system_time.call(null) - start__4626__auto__).toFixed((6)))," msecs"].join(''));

return ret__4627__auto__;
})());
seven_guis.cells.cols = (26);
seven_guis.cells.rows = (100);
seven_guis.cells.timeout = (2000);
seven_guis.cells.letters = cljs.core.map.call(null,cljs.core.comp.call(null,cljs.core.char$,(function (p1__68303_SHARP_){
return ((97) + p1__68303_SHARP_);
})),cljs.core.range.call(null,seven_guis.cells.cols));
seven_guis.cells.test_strings = new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, ["hello =1","hello=$B1"], null);
seven_guis.cells.letter__GT_idx = (function seven_guis$cells$letter__GT_idx(letter){
return (letter.charCodeAt((0)) - ((cljs.core._EQ_.call(null,letter.toUpperCase(),letter))?(65):(97)));
});
seven_guis.cells.copy_text = (function seven_guis$cells$copy_text(text){
return (function (){
return navigator.clipboard.writeText(text);
});
});
seven_guis.cells.string__GT_ast = instaparse.core.map__GT_Parser.call(null,instaparse.core.parser.call(null,cljs.core.PersistentHashMap.fromArrays([new cljs.core.Keyword(null,"expression","expression",202311876),new cljs.core.Keyword(null,"number","number",1570378438),new cljs.core.Keyword(null,"ref","ref",1289896967),new cljs.core.Keyword(null,"naked-operation","naked-operation",240130376),new cljs.core.Keyword(null,"operation","operation",-1267664310),new cljs.core.Keyword(null,"op","op",-1882987955),new cljs.core.Keyword(null,"toplevel","toplevel",1423972654),new cljs.core.Keyword(null,"described","described",-1297878603),new cljs.core.Keyword(null,"wrapped-operation","wrapped-operation",1993790876),new cljs.core.Keyword(null,"text","text",-1790561697)],[new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"alt","alt",-3214426),new cljs.core.Keyword(null,"parsers","parsers",-804353827),(new cljs.core.List(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"nt","nt",-835425781),new cljs.core.Keyword(null,"keyword","keyword",811389747),new cljs.core.Keyword(null,"number","number",1570378438)], null),(new cljs.core.List(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"nt","nt",-835425781),new cljs.core.Keyword(null,"keyword","keyword",811389747),new cljs.core.Keyword(null,"operation","operation",-1267664310)], null),(new cljs.core.List(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"nt","nt",-835425781),new cljs.core.Keyword(null,"keyword","keyword",811389747),new cljs.core.Keyword(null,"ref","ref",1289896967)], null),null,(1),null)),(2),null)),(3),null)),new cljs.core.Keyword(null,"red","red",-969428204),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"reduction-type","reduction-type",-488293450),new cljs.core.Keyword(null,"raw","raw",1604651272)], null)], null),cljs.core.merge.call(null,instaparse.combinators_source.regexp.call(null,"\\d+"),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"red","red",-969428204),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"reduction-type","reduction-type",-488293450),new cljs.core.Keyword(null,"hiccup","hiccup",1218876238),new cljs.core.Keyword(null,"key","key",-1516042587),new cljs.core.Keyword(null,"number","number",1570378438)], null)], null)),new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"cat","cat",-1457810207),new cljs.core.Keyword(null,"parsers","parsers",-804353827),(new cljs.core.List(null,cljs.core.merge.call(null,instaparse.combinators_source.regexp.call(null,"[A-Za-z]"),cljs.core.PersistentArrayMap.EMPTY),(new cljs.core.List(null,cljs.core.merge.call(null,instaparse.combinators_source.regexp.call(null,"[0-9]{1,2}"),cljs.core.PersistentArrayMap.EMPTY),null,(1),null)),(2),null)),new cljs.core.Keyword(null,"red","red",-969428204),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"reduction-type","reduction-type",-488293450),new cljs.core.Keyword(null,"hiccup","hiccup",1218876238),new cljs.core.Keyword(null,"key","key",-1516042587),new cljs.core.Keyword(null,"ref","ref",1289896967)], null)], null),new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"cat","cat",-1457810207),new cljs.core.Keyword(null,"parsers","parsers",-804353827),(new cljs.core.List(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"nt","nt",-835425781),new cljs.core.Keyword(null,"keyword","keyword",811389747),new cljs.core.Keyword(null,"expression","expression",202311876)], null),(new cljs.core.List(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"nt","nt",-835425781),new cljs.core.Keyword(null,"keyword","keyword",811389747),new cljs.core.Keyword(null,"op","op",-1882987955)], null),(new cljs.core.List(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"nt","nt",-835425781),new cljs.core.Keyword(null,"keyword","keyword",811389747),new cljs.core.Keyword(null,"expression","expression",202311876)], null),null,(1),null)),(2),null)),(3),null)),new cljs.core.Keyword(null,"red","red",-969428204),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"reduction-type","reduction-type",-488293450),new cljs.core.Keyword(null,"hiccup","hiccup",1218876238),new cljs.core.Keyword(null,"key","key",-1516042587),new cljs.core.Keyword(null,"naked-operation","naked-operation",240130376)], null)], null),new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"alt","alt",-3214426),new cljs.core.Keyword(null,"parsers","parsers",-804353827),(new cljs.core.List(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"nt","nt",-835425781),new cljs.core.Keyword(null,"keyword","keyword",811389747),new cljs.core.Keyword(null,"naked-operation","naked-operation",240130376)], null),(new cljs.core.List(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"nt","nt",-835425781),new cljs.core.Keyword(null,"keyword","keyword",811389747),new cljs.core.Keyword(null,"wrapped-operation","wrapped-operation",1993790876)], null),null,(1),null)),(2),null)),new cljs.core.Keyword(null,"red","red",-969428204),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"reduction-type","reduction-type",-488293450),new cljs.core.Keyword(null,"raw","raw",1604651272)], null)], null),cljs.core.merge.call(null,instaparse.combinators_source.regexp.call(null,"[\\+\\-\\*\\/]"),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"red","red",-969428204),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"reduction-type","reduction-type",-488293450),new cljs.core.Keyword(null,"hiccup","hiccup",1218876238),new cljs.core.Keyword(null,"key","key",-1516042587),new cljs.core.Keyword(null,"op","op",-1882987955)], null)], null)),new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"ord","ord",1142548323),new cljs.core.Keyword(null,"parser1","parser1",-439601422),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"nt","nt",-835425781),new cljs.core.Keyword(null,"keyword","keyword",811389747),new cljs.core.Keyword(null,"number","number",1570378438)], null),new cljs.core.Keyword(null,"parser2","parser2",1013754688),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"alt","alt",-3214426),new cljs.core.Keyword(null,"parsers","parsers",-804353827),(new cljs.core.List(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"nt","nt",-835425781),new cljs.core.Keyword(null,"keyword","keyword",811389747),new cljs.core.Keyword(null,"text","text",-1790561697)], null),(new cljs.core.List(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"nt","nt",-835425781),new cljs.core.Keyword(null,"keyword","keyword",811389747),new cljs.core.Keyword(null,"described","described",-1297878603)], null),(new cljs.core.List(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"cat","cat",-1457810207),new cljs.core.Keyword(null,"parsers","parsers",-804353827),(new cljs.core.List(null,new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"string","string",-1989541586),new cljs.core.Keyword(null,"string","string",-1989541586),"=",new cljs.core.Keyword(null,"hide","hide",-596913169),true], null),(new cljs.core.List(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"nt","nt",-835425781),new cljs.core.Keyword(null,"keyword","keyword",811389747),new cljs.core.Keyword(null,"expression","expression",202311876)], null),null,(1),null)),(2),null))], null),null,(1),null)),(2),null)),(3),null))], null),new cljs.core.Keyword(null,"red","red",-969428204),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"reduction-type","reduction-type",-488293450),new cljs.core.Keyword(null,"hiccup","hiccup",1218876238),new cljs.core.Keyword(null,"key","key",-1516042587),new cljs.core.Keyword(null,"toplevel","toplevel",1423972654)], null)], null),new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"cat","cat",-1457810207),new cljs.core.Keyword(null,"parsers","parsers",-804353827),(new cljs.core.List(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"nt","nt",-835425781),new cljs.core.Keyword(null,"keyword","keyword",811389747),new cljs.core.Keyword(null,"text","text",-1790561697)], null),(new cljs.core.List(null,new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"string","string",-1989541586),new cljs.core.Keyword(null,"string","string",-1989541586),"=",new cljs.core.Keyword(null,"hide","hide",-596913169),true], null),(new cljs.core.List(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"nt","nt",-835425781),new cljs.core.Keyword(null,"keyword","keyword",811389747),new cljs.core.Keyword(null,"expression","expression",202311876)], null),null,(1),null)),(2),null)),(3),null)),new cljs.core.Keyword(null,"red","red",-969428204),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"reduction-type","reduction-type",-488293450),new cljs.core.Keyword(null,"hiccup","hiccup",1218876238),new cljs.core.Keyword(null,"key","key",-1516042587),new cljs.core.Keyword(null,"described","described",-1297878603)], null)], null),new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"cat","cat",-1457810207),new cljs.core.Keyword(null,"parsers","parsers",-804353827),(new cljs.core.List(null,new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"string","string",-1989541586),new cljs.core.Keyword(null,"string","string",-1989541586),"(",new cljs.core.Keyword(null,"hide","hide",-596913169),true], null),(new cljs.core.List(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"nt","nt",-835425781),new cljs.core.Keyword(null,"keyword","keyword",811389747),new cljs.core.Keyword(null,"operation","operation",-1267664310)], null),(new cljs.core.List(null,new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Keyword(null,"string","string",-1989541586),new cljs.core.Keyword(null,"string","string",-1989541586),")",new cljs.core.Keyword(null,"hide","hide",-596913169),true], null),null,(1),null)),(2),null)),(3),null)),new cljs.core.Keyword(null,"red","red",-969428204),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"reduction-type","reduction-type",-488293450),new cljs.core.Keyword(null,"raw","raw",1604651272)], null)], null),cljs.core.merge.call(null,instaparse.combinators_source.regexp.call(null,"[^=]+"),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"red","red",-969428204),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"reduction-type","reduction-type",-488293450),new cljs.core.Keyword(null,"raw","raw",1604651272)], null)], null))]),new cljs.core.Keyword(null,"start","start",-355208981),new cljs.core.Keyword(null,"toplevel","toplevel",1423972654),new cljs.core.Keyword(null,"auto-whitespace","auto-whitespace",741152317),new cljs.core.Keyword(null,"standard","standard",-1769206695)));
/**
 * convert numbers and operations to their own types, and convert letter cols to numbers
 */
seven_guis.cells.clean_ast = (function seven_guis$cells$clean_ast(ast){
var ast__$1 = instaparse.core.transform.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"number","number",1570378438),parseInt,new cljs.core.Keyword(null,"ref","ref",1289896967),(function (letter,number){
return (new cljs.core.PersistentVector(null,3,(5),cljs.core.PersistentVector.EMPTY_NODE,[new cljs.core.Keyword(null,"ref","ref",1289896967),parseInt(number),seven_guis.cells.letter__GT_idx.call(null,cljs.core.str.cljs$core$IFn$_invoke$arity$1(letter))],null));
})], null),ast);
return ast__$1;
});
/**
 * scrape all cell refs from the ast into a set
 */
seven_guis.cells.ast__GT_links = (function seven_guis$cells$ast__GT_links(ast){
var smoosh = (function() { 
var G__68305__delegate = function (args){
return cljs.core.into.call(null,cljs.core.PersistentHashSet.EMPTY,cljs.core.mapcat.call(null,(function (p1__68304_SHARP_){
if(cljs.core.set_QMARK_.call(null,p1__68304_SHARP_)){
return p1__68304_SHARP_;
} else {
return cljs.core.PersistentHashSet.EMPTY;
}
}),args));
};
var G__68305 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__68306__i = 0, G__68306__a = new Array(arguments.length -  0);
while (G__68306__i < G__68306__a.length) {G__68306__a[G__68306__i] = arguments[G__68306__i + 0]; ++G__68306__i;}
  args = new cljs.core.IndexedSeq(G__68306__a,0,null);
} 
return G__68305__delegate.call(this,args);};
G__68305.cljs$lang$maxFixedArity = 0;
G__68305.cljs$lang$applyTo = (function (arglist__68307){
var args = cljs.core.seq(arglist__68307);
return G__68305__delegate(args);
});
G__68305.cljs$core$IFn$_invoke$arity$variadic = G__68305__delegate;
return G__68305;
})()
;
return instaparse.core.transform.call(null,new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"ref","ref",1289896967),(function (col,row){
return cljs.core.PersistentHashSet.createAsIfByAssoc([new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [col,row], null)]);
}),new cljs.core.Keyword(null,"toplevel","toplevel",1423972654),smoosh,new cljs.core.Keyword(null,"described","described",-1297878603),smoosh,new cljs.core.Keyword(null,"naked-operation","naked-operation",240130376),smoosh], null),ast);
});
seven_guis.cells.add_backlinks = (function seven_guis$cells$add_backlinks(state,pos){
return cljs.core.assoc.call(null,state,new cljs.core.Keyword(null,"cells","cells",-985166822),cljs.core.reduce.call(null,(function (cells,link){
return cljs.core.assoc_in.call(null,cells,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [link,new cljs.core.Keyword(null,"backlinks","backlinks",1645357998)], null),cljs.core.conj.call(null,(function (){var or__4126__auto__ = cljs.core.get_in.call(null,cells,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [link,new cljs.core.Keyword(null,"backlinks","backlinks",1645357998)], null));
if(cljs.core.truth_(or__4126__auto__)){
return or__4126__auto__;
} else {
return cljs.core.PersistentHashSet.EMPTY;
}
})(),pos));
}),new cljs.core.Keyword(null,"cells","cells",-985166822).cljs$core$IFn$_invoke$arity$1(state),cljs.core.get_in.call(null,state,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"cells","cells",-985166822),pos,new cljs.core.Keyword(null,"links","links",-654507394)], null))));
});
/**
 * remove backlinks from old equation
 */
seven_guis.cells.remove_backlinks = (function seven_guis$cells$remove_backlinks(state,pos){
return cljs.core.assoc.call(null,state,new cljs.core.Keyword(null,"cells","cells",-985166822),cljs.core.reduce.call(null,(function (cells,link){
return cljs.core.assoc_in.call(null,cells,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [link,new cljs.core.Keyword(null,"backlinks","backlinks",1645357998)], null),cljs.core.disj.call(null,(function (){var or__4126__auto__ = cljs.core.get_in.call(null,cells,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [link,new cljs.core.Keyword(null,"backlinks","backlinks",1645357998)], null));
if(cljs.core.truth_(or__4126__auto__)){
return or__4126__auto__;
} else {
return cljs.core.PersistentHashSet.EMPTY;
}
})(),pos));
}),new cljs.core.Keyword(null,"cells","cells",-985166822).cljs$core$IFn$_invoke$arity$1(state),cljs.core.get_in.call(null,state,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"cells","cells",-985166822),pos,new cljs.core.Keyword(null,"links","links",-654507394)], null))));
});
/**
 * Adds cell from string, including ast, backlinks
 */
seven_guis.cells.add_cell = (function seven_guis$cells$add_cell(state,pos,string){
var ast = seven_guis.cells.string__GT_ast.call(null,string);
if(instaparse.core.failure_QMARK_.call(null,ast)){
return cljs.core.update_in.call(null,state,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"cells","cells",-985166822),pos], null),(function (p1__68308_SHARP_){
return cljs.core.merge.call(null,p1__68308_SHARP_,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"raw","raw",1604651272),string,new cljs.core.Keyword(null,"display","display",242065432),string], null));
}));
} else {
var ast__$1 = seven_guis.cells.clean_ast.call(null,ast);
var links = seven_guis.cells.ast__GT_links.call(null,ast__$1);
var state__$1 = seven_guis.cells.remove_backlinks.call(null,state,pos);
var state__$2 = cljs.core.update_in.call(null,state__$1,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"cells","cells",-985166822),pos], null),(function (p1__68309_SHARP_){
return cljs.core.merge.call(null,p1__68309_SHARP_,new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"raw","raw",1604651272),string,new cljs.core.Keyword(null,"ast","ast",-860334068),ast__$1,new cljs.core.Keyword(null,"display","display",242065432),string,new cljs.core.Keyword(null,"links","links",-654507394),links], null));
}));
var state__$3 = seven_guis.cells.add_backlinks.call(null,state__$2,pos);
var state__$4 = cljs.core.update.call(null,state__$3,new cljs.core.Keyword(null,"dirty","dirty",729553281),(function (p1__68310_SHARP_){
return cljs.core.conj.call(null,p1__68310_SHARP_,pos);
}));
return state__$4;
}
});
/**
 * Adds cell from string, including ast, backlinks
 */
seven_guis.cells.parse_string = (function seven_guis$cells$parse_string(string){
var ast = seven_guis.cells.string__GT_ast.call(null,string);
if(instaparse.core.failure_QMARK_.call(null,ast)){
return cljs.core.PersistentArrayMap.EMPTY;
} else {
var ast__$1 = seven_guis.cells.clean_ast.call(null,ast);
var links = seven_guis.cells.ast__GT_links.call(null,ast__$1);
return new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"ast","ast",-860334068),ast__$1,new cljs.core.Keyword(null,"links","links",-654507394),links], null);
}
});
seven_guis.cells.handle_key = (function seven_guis$cells$handle_key(state_atom,pos,event){
var key = event.key;
var shift = event.shiftKey;
var move_direction = (function (){var G__68313 = key;
switch (G__68313) {
case "Tab":
if(cljs.core.truth_(shift)){
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(0),(-1)], null);
} else {
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(0),(1)], null);
}

break;
case "ArrowDown":
case "Enter":
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(1),(0)], null);

break;
case "ArrowUp":
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(-1),(0)], null);

break;
default:
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(0),(0)], null);

}
})();
var constrain = (function (p1__68312_SHARP_,p2__68311_SHARP_){
var x__4214__auto__ = (0);
var y__4215__auto__ = (function (){var x__4217__auto__ = p2__68311_SHARP_;
var y__4218__auto__ = p1__68312_SHARP_;
return ((x__4217__auto__ < y__4218__auto__) ? x__4217__auto__ : y__4218__auto__);
})();
return ((x__4214__auto__ > y__4215__auto__) ? x__4214__auto__ : y__4215__auto__);
});
var constrain_pos = (function (p__68314){
var vec__68315 = p__68314;
var i = cljs.core.nth.call(null,vec__68315,(0),null);
var j = cljs.core.nth.call(null,vec__68315,(1),null);
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [constrain.call(null,i,seven_guis.cells.rows),constrain.call(null,j,seven_guis.cells.cols)], null);
});
var new_editing = constrain_pos.call(null,cljs.core.mapv.call(null,cljs.core._PLUS_,pos,move_direction));
if(cljs.core.not_EQ_.call(null,new_editing,pos)){
return cljs.core.swap_BANG_.call(null,state_atom,cljs.core.assoc,new cljs.core.Keyword(null,"editing","editing",1365491601),new_editing);
} else {
return null;
}
});
seven_guis.cells.paste_csv = (function seven_guis$cells$paste_csv(p__68322,csv){
var map__68323 = p__68322;
var map__68323__$1 = (((((!((map__68323 == null))))?(((((map__68323.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__68323.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__68323):map__68323);
var state = map__68323__$1;
var vec__68324 = cljs.core.get.call(null,map__68323__$1,new cljs.core.Keyword(null,"editing","editing",1365491601));
var row_e = cljs.core.nth.call(null,vec__68324,(0),null);
var col_e = cljs.core.nth.call(null,vec__68324,(1),null);
var lines = cljs.core.mapv.call(null,(function (p1__68319_SHARP_){
return cljs.core.vec.call(null,clojure.string.split.call(null,p1__68319_SHARP_,","));
}),clojure.string.split.call(null,csv,"\n"));
var rows = cljs.core.count.call(null,lines);
var cols = cljs.core.apply.call(null,cljs.core.max,cljs.core.map.call(null,cljs.core.count,lines));
var updates = (function (){var iter__4529__auto__ = (function seven_guis$cells$paste_csv_$_iter__68328(s__68329){
return (new cljs.core.LazySeq(null,(function (){
var s__68329__$1 = s__68329;
while(true){
var temp__5735__auto__ = cljs.core.seq.call(null,s__68329__$1);
if(temp__5735__auto__){
var xs__6292__auto__ = temp__5735__auto__;
var row = cljs.core.first.call(null,xs__6292__auto__);
var iterys__4525__auto__ = ((function (s__68329__$1,row,xs__6292__auto__,temp__5735__auto__,lines,rows,cols,map__68323,map__68323__$1,state,vec__68324,row_e,col_e){
return (function seven_guis$cells$paste_csv_$_iter__68328_$_iter__68330(s__68331){
return (new cljs.core.LazySeq(null,((function (s__68329__$1,row,xs__6292__auto__,temp__5735__auto__,lines,rows,cols,map__68323,map__68323__$1,state,vec__68324,row_e,col_e){
return (function (){
var s__68331__$1 = s__68331;
while(true){
var temp__5735__auto____$1 = cljs.core.seq.call(null,s__68331__$1);
if(temp__5735__auto____$1){
var s__68331__$2 = temp__5735__auto____$1;
if(cljs.core.chunked_seq_QMARK_.call(null,s__68331__$2)){
var c__4527__auto__ = cljs.core.chunk_first.call(null,s__68331__$2);
var size__4528__auto__ = cljs.core.count.call(null,c__4527__auto__);
var b__68333 = cljs.core.chunk_buffer.call(null,size__4528__auto__);
if((function (){var i__68332 = (0);
while(true){
if((i__68332 < size__4528__auto__)){
var col = cljs.core._nth.call(null,c__4527__auto__,i__68332);
cljs.core.chunk_append.call(null,b__68333,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(row + row_e),(col + col_e)], null),cljs.core.get_in.call(null,lines,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [row,col], null))], null));

var G__68340 = (i__68332 + (1));
i__68332 = G__68340;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons.call(null,cljs.core.chunk.call(null,b__68333),seven_guis$cells$paste_csv_$_iter__68328_$_iter__68330.call(null,cljs.core.chunk_rest.call(null,s__68331__$2)));
} else {
return cljs.core.chunk_cons.call(null,cljs.core.chunk.call(null,b__68333),null);
}
} else {
var col = cljs.core.first.call(null,s__68331__$2);
return cljs.core.cons.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(row + row_e),(col + col_e)], null),cljs.core.get_in.call(null,lines,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [row,col], null))], null),seven_guis$cells$paste_csv_$_iter__68328_$_iter__68330.call(null,cljs.core.rest.call(null,s__68331__$2)));
}
} else {
return null;
}
break;
}
});})(s__68329__$1,row,xs__6292__auto__,temp__5735__auto__,lines,rows,cols,map__68323,map__68323__$1,state,vec__68324,row_e,col_e))
,null,null));
});})(s__68329__$1,row,xs__6292__auto__,temp__5735__auto__,lines,rows,cols,map__68323,map__68323__$1,state,vec__68324,row_e,col_e))
;
var fs__4526__auto__ = cljs.core.seq.call(null,iterys__4525__auto__.call(null,cljs.core.range.call(null,cols)));
if(fs__4526__auto__){
return cljs.core.concat.call(null,fs__4526__auto__,seven_guis$cells$paste_csv_$_iter__68328.call(null,cljs.core.rest.call(null,s__68329__$1)));
} else {
var G__68341 = cljs.core.rest.call(null,s__68329__$1);
s__68329__$1 = G__68341;
continue;
}
} else {
return null;
}
break;
}
}),null,null));
});
return iter__4529__auto__.call(null,cljs.core.range.call(null,rows));
})();
var parsed = (function (){var start__4626__auto__ = cljs.core.system_time.call(null);
var ret__4627__auto__ = (function (){var iter__4529__auto__ = (function seven_guis$cells$paste_csv_$_iter__68334(s__68335){
return (new cljs.core.LazySeq(null,(function (){
var s__68335__$1 = s__68335;
while(true){
var temp__5735__auto__ = cljs.core.seq.call(null,s__68335__$1);
if(temp__5735__auto__){
var xs__6292__auto__ = temp__5735__auto__;
var row = cljs.core.first.call(null,xs__6292__auto__);
var iterys__4525__auto__ = ((function (s__68335__$1,row,xs__6292__auto__,temp__5735__auto__,start__4626__auto__,lines,rows,cols,updates,map__68323,map__68323__$1,state,vec__68324,row_e,col_e){
return (function seven_guis$cells$paste_csv_$_iter__68334_$_iter__68336(s__68337){
return (new cljs.core.LazySeq(null,((function (s__68335__$1,row,xs__6292__auto__,temp__5735__auto__,start__4626__auto__,lines,rows,cols,updates,map__68323,map__68323__$1,state,vec__68324,row_e,col_e){
return (function (){
var s__68337__$1 = s__68337;
while(true){
var temp__5735__auto____$1 = cljs.core.seq.call(null,s__68337__$1);
if(temp__5735__auto____$1){
var s__68337__$2 = temp__5735__auto____$1;
if(cljs.core.chunked_seq_QMARK_.call(null,s__68337__$2)){
var c__4527__auto__ = cljs.core.chunk_first.call(null,s__68337__$2);
var size__4528__auto__ = cljs.core.count.call(null,c__4527__auto__);
var b__68339 = cljs.core.chunk_buffer.call(null,size__4528__auto__);
if((function (){var i__68338 = (0);
while(true){
if((i__68338 < size__4528__auto__)){
var col = cljs.core._nth.call(null,c__4527__auto__,i__68338);
cljs.core.chunk_append.call(null,b__68339,seven_guis.cells.parse_string.call(null,cljs.core.get_in.call(null,lines,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [row,col], null))));

var G__68342 = (i__68338 + (1));
i__68338 = G__68342;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons.call(null,cljs.core.chunk.call(null,b__68339),seven_guis$cells$paste_csv_$_iter__68334_$_iter__68336.call(null,cljs.core.chunk_rest.call(null,s__68337__$2)));
} else {
return cljs.core.chunk_cons.call(null,cljs.core.chunk.call(null,b__68339),null);
}
} else {
var col = cljs.core.first.call(null,s__68337__$2);
return cljs.core.cons.call(null,seven_guis.cells.parse_string.call(null,cljs.core.get_in.call(null,lines,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [row,col], null))),seven_guis$cells$paste_csv_$_iter__68334_$_iter__68336.call(null,cljs.core.rest.call(null,s__68337__$2)));
}
} else {
return null;
}
break;
}
});})(s__68335__$1,row,xs__6292__auto__,temp__5735__auto__,start__4626__auto__,lines,rows,cols,updates,map__68323,map__68323__$1,state,vec__68324,row_e,col_e))
,null,null));
});})(s__68335__$1,row,xs__6292__auto__,temp__5735__auto__,start__4626__auto__,lines,rows,cols,updates,map__68323,map__68323__$1,state,vec__68324,row_e,col_e))
;
var fs__4526__auto__ = cljs.core.seq.call(null,iterys__4525__auto__.call(null,cljs.core.range.call(null,cols)));
if(fs__4526__auto__){
return cljs.core.concat.call(null,fs__4526__auto__,seven_guis$cells$paste_csv_$_iter__68334.call(null,cljs.core.rest.call(null,s__68335__$1)));
} else {
var G__68343 = cljs.core.rest.call(null,s__68335__$1);
s__68335__$1 = G__68343;
continue;
}
} else {
return null;
}
break;
}
}),null,null));
});
return iter__4529__auto__.call(null,cljs.core.range.call(null,rows));
})();
cljs.core.prn.call(null,["Elapsed time: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1((cljs.core.system_time.call(null) - start__4626__auto__).toFixed((6)))," msecs"].join(''));

return ret__4627__auto__;
})();
var state__$1 = (function (){var start__4626__auto__ = cljs.core.system_time.call(null);
var ret__4627__auto__ = cljs.core.reduce.call(null,(function (p1__68320_SHARP_,p2__68321_SHARP_){
return cljs.core.apply.call(null,seven_guis.cells.add_cell,p1__68320_SHARP_,p2__68321_SHARP_);
}),state,updates);
cljs.core.prn.call(null,["Elapsed time: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1((cljs.core.system_time.call(null) - start__4626__auto__).toFixed((6)))," msecs"].join(''));

return ret__4627__auto__;
})();
if(cljs.core._EQ_.call(null,(1),(1))){
return state__$1;
} else {
return parsed;
}
});
/**
 * Propagate dirty status to dependents
 */
seven_guis.cells.trace_dirty = (function seven_guis$cells$trace_dirty(p__68344){
var map__68345 = p__68344;
var map__68345__$1 = (((((!((map__68345 == null))))?(((((map__68345.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__68345.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__68345):map__68345);
var state = map__68345__$1;
var dirty = cljs.core.get.call(null,map__68345__$1,new cljs.core.Keyword(null,"dirty","dirty",729553281));
var todo = dirty;
var done = cljs.core.PersistentHashSet.EMPTY;
while(true){
if(cljs.core.empty_QMARK_.call(null,todo)){
return cljs.core.assoc.call(null,state,new cljs.core.Keyword(null,"dirty","dirty",729553281),done);
} else {
var cur = cljs.core.first.call(null,todo);
var new_done = cljs.core.conj.call(null,done,cur);
var deps = cljs.core.get_in.call(null,state,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"cells","cells",-985166822),cur,new cljs.core.Keyword(null,"backlinks","backlinks",1645357998)], null));
var novel_deps = cljs.core.filter.call(null,cljs.core.comp.call(null,cljs.core.not,done),deps);
var new_todo = cljs.core.into.call(null,cljs.core.disj.call(null,todo,cur),novel_deps);
var G__68347 = new_todo;
var G__68348 = new_done;
todo = G__68347;
done = G__68348;
continue;
}
break;
}
});
seven_guis.cells.evaluate = (function seven_guis$cells$evaluate(state,pos){
var equation = cljs.core.get_in.call(null,state,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"cells","cells",-985166822),pos,new cljs.core.Keyword(null,"ast","ast",-860334068)], null));
var get_number = (function (i,j){
return cljs.core.get_in.call(null,state,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"cells","cells",-985166822),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [i,j], null),new cljs.core.Keyword(null,"number","number",1570378438)], null),(0));
});
var described = instaparse.core.transform.call(null,new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"ref","ref",1289896967),get_number,new cljs.core.Keyword(null,"op","op",-1882987955),new cljs.core.PersistentArrayMap(null, 4, ["+",cljs.core._PLUS_,"-",cljs.core._,"*",cljs.core._STAR_,"/",cljs.core._SLASH_], null),new cljs.core.Keyword(null,"naked-operation","naked-operation",240130376),(function (a,op,b){
return op.call(null,a,b);
})], null),equation);
var number = (function (){var result = cljs.core.second.call(null,instaparse.core.transform.call(null,new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"described","described",-1297878603),(function (p1__68350_SHARP_,p2__68349_SHARP_){
return cljs.core.identity.call(null,p2__68349_SHARP_);
})], null),described));
return result;
})();
var display = cljs.core.second.call(null,instaparse.core.transform.call(null,new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"described","described",-1297878603),(function (text,number__$1){
return [cljs.core.str.cljs$core$IFn$_invoke$arity$1(text),"=",cljs.core.str.cljs$core$IFn$_invoke$arity$1(number__$1)].join('');
})], null),described));
return cljs.core.update_in.call(null,state,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"cells","cells",-985166822),pos], null),(function (p1__68351_SHARP_){
return cljs.core.assoc.call(null,p1__68351_SHARP_,new cljs.core.Keyword(null,"number","number",1570378438),number,new cljs.core.Keyword(null,"display","display",242065432),display);
}));
});
seven_guis.cells.evaluate_cells = (function seven_guis$cells$evaluate_cells(state){
var state__$1 = seven_guis.cells.trace_dirty.call(null,state);
var to_dirty_pair = (function (state__$2,pos){
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.count.call(null,cljs.core.filter.call(null,new cljs.core.Keyword(null,"dirty","dirty",729553281).cljs$core$IFn$_invoke$arity$1(state__$2),cljs.core.get_in.call(null,state__$2,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"cells","cells",-985166822),pos,new cljs.core.Keyword(null,"links","links",-654507394)], null)))),pos], null);
});
var by_dirty = cljs.core.into.call(null,cljs.core.sorted_set.call(null),cljs.core.map.call(null,(function (p1__68352_SHARP_){
return to_dirty_pair.call(null,state__$1,p1__68352_SHARP_);
})),new cljs.core.Keyword(null,"dirty","dirty",729553281).cljs$core$IFn$_invoke$arity$1(state__$1));
var dirty_map = cljs.core.into.call(null,cljs.core.PersistentArrayMap.EMPTY,cljs.core.map.call(null,cljs.core.comp.call(null,cljs.core.vec,cljs.core.reverse)),by_dirty);
var by_dirty__$1 = by_dirty;
var dirty_map__$1 = dirty_map;
var state__$2 = state__$1;
while(true){
if(cljs.core.empty_QMARK_.call(null,by_dirty__$1)){
return cljs.core.assoc.call(null,state__$2,new cljs.core.Keyword(null,"dirty","dirty",729553281),cljs.core.PersistentHashSet.EMPTY);
} else {
if(((0) > cljs.core.first.call(null,cljs.core.first.call(null,by_dirty__$1)))){
alert("infinite loop. please remove");

return cljs.core.assoc.call(null,state__$2,new cljs.core.Keyword(null,"dirty","dirty",729553281),cljs.core.PersistentHashSet.EMPTY);
} else {
var cur_pair = cljs.core.first.call(null,by_dirty__$1);
var by_dirty__$2 = cljs.core.disj.call(null,by_dirty__$1,cur_pair);
var cur = cljs.core.second.call(null,cur_pair);
var dirty_map__$2 = cljs.core.update.call(null,dirty_map__$1,cur,cljs.core.dec);
var backlinks = cljs.core.get_in.call(null,state__$2,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"cells","cells",-985166822),cur,new cljs.core.Keyword(null,"backlinks","backlinks",1645357998)], null));
var by_dirty__$3 = cljs.core.reduce.call(null,((function (by_dirty__$1,dirty_map__$1,state__$2,cur_pair,by_dirty__$2,cur,dirty_map__$2,backlinks,state__$1,to_dirty_pair,by_dirty,dirty_map){
return (function (p1__68353_SHARP_,p2__68354_SHARP_){
return cljs.core.conj.call(null,cljs.core.disj.call(null,p1__68353_SHARP_,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [dirty_map__$2.call(null,p2__68354_SHARP_),p2__68354_SHARP_], null)),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(dirty_map__$2.call(null,p2__68354_SHARP_) - (1)),p2__68354_SHARP_], null));
});})(by_dirty__$1,dirty_map__$1,state__$2,cur_pair,by_dirty__$2,cur,dirty_map__$2,backlinks,state__$1,to_dirty_pair,by_dirty,dirty_map))
,by_dirty__$2,backlinks);
var dirty_map__$3 = cljs.core.reduce.call(null,((function (by_dirty__$1,dirty_map__$1,state__$2,cur_pair,by_dirty__$2,cur,dirty_map__$2,backlinks,by_dirty__$3,state__$1,to_dirty_pair,by_dirty,dirty_map){
return (function (p1__68355_SHARP_,p2__68356_SHARP_){
return cljs.core.update.call(null,p1__68355_SHARP_,p2__68356_SHARP_,cljs.core.dec);
});})(by_dirty__$1,dirty_map__$1,state__$2,cur_pair,by_dirty__$2,cur,dirty_map__$2,backlinks,by_dirty__$3,state__$1,to_dirty_pair,by_dirty,dirty_map))
,dirty_map__$2,backlinks);
var state__$3 = seven_guis.cells.evaluate.call(null,state__$2,cur);
var G__68357 = by_dirty__$3;
var G__68358 = dirty_map__$3;
var G__68359 = state__$3;
by_dirty__$1 = G__68357;
dirty_map__$1 = G__68358;
state__$2 = G__68359;
continue;
}
}
break;
}
});
seven_guis.cells.cell = (function seven_guis$cells$cell(state,cell,pos){
return cljs.core.with_meta(new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"td","td",1479933353),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"class","class",-2030961996),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(cljs.core.truth_(new cljs.core.Keyword(null,"syntax-error?","syntax-error?",1909791905).cljs$core$IFn$_invoke$arity$1(cell))?"syntax-error":null),(cljs.core.truth_(new cljs.core.Keyword(null,"ast?","ast?",-170307757).cljs$core$IFn$_invoke$arity$1(cell))?"equation":null)], null),new cljs.core.Keyword(null,"on-click","on-click",1632826543),(function (){
return cljs.core.swap_BANG_.call(null,state,cljs.core.assoc,new cljs.core.Keyword(null,"editing","editing",1365491601),pos);
})], null),new cljs.core.Keyword(null,"display","display",242065432).cljs$core$IFn$_invoke$arity$1(cell)], null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"key","key",-1516042587),pos.call(null,(1))], null));
});
seven_guis.cells.editing_cell = (function seven_guis$cells$editing_cell(state,cell,pos){
return cljs.core.with_meta(new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"td#editing","td#editing",-37100199),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"input","input",556931961),new cljs.core.PersistentArrayMap(null, 5, [new cljs.core.Keyword(null,"auto-focus","auto-focus",1250006231),true,new cljs.core.Keyword(null,"default-value","default-value",232220170),new cljs.core.Keyword(null,"raw","raw",1604651272).cljs$core$IFn$_invoke$arity$1(cell),new cljs.core.Keyword(null,"on-paste","on-paste",-50859856),(function (p1__68360_SHARP_){
p1__68360_SHARP_.preventDefault();

return cljs.core.swap_BANG_.call(null,state,seven_guis.cells.paste_csv,p1__68360_SHARP_.clipboardData.getData("text"));
}),new cljs.core.Keyword(null,"on-change","on-change",-732046149),(function (p1__68361_SHARP_){
return cljs.core.swap_BANG_.call(null,state,seven_guis.cells.add_cell,pos,p1__68361_SHARP_.target.value);
}),new cljs.core.Keyword(null,"on-key-down","on-key-down",-1374733765),(function (p1__68362_SHARP_){
return seven_guis.cells.handle_key.call(null,state,pos,p1__68362_SHARP_);
})], null)], null)], null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"key","key",-1516042587),pos.call(null,(1))], null));
});
/**
 * it's a spreadsheet. it's virtue is it's less code than real spreadsheets
 */
seven_guis.cells.cells = (function seven_guis$cells$cells(){
var state = reagent.core.atom.call(null,new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"cells","cells",-985166822),cljs.core.PersistentArrayMap.createAsIfByAssoc([new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(1),(0)], null),new cljs.core.PersistentArrayMap(null, 6, [new cljs.core.Keyword(null,"ast","ast",-860334068),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"toplevel","toplevel",1423972654),(1)], null),new cljs.core.Keyword(null,"number","number",1570378438),(1),new cljs.core.Keyword(null,"raw","raw",1604651272),"1",new cljs.core.Keyword(null,"display","display",242065432),"1",new cljs.core.Keyword(null,"links","links",-654507394),cljs.core.PersistentHashSet.EMPTY,new cljs.core.Keyword(null,"backlinks","backlinks",1645357998),cljs.core.PersistentHashSet.EMPTY], null)]),new cljs.core.Keyword(null,"dirty","dirty",729553281),cljs.core.PersistentHashSet.EMPTY,new cljs.core.Keyword(null,"editing","editing",1365491601),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(0),(0)], null)], null));
return reagent.core.create_class.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"component-did-update","component-did-update",-1468549173),(function (){
if(cljs.core.truth_(cljs.core.not_empty.call(null,new cljs.core.Keyword(null,"dirty","dirty",729553281).cljs$core$IFn$_invoke$arity$1(cljs.core.deref.call(null,state))))){
return cljs.core.swap_BANG_.call(null,state,seven_guis.cells.evaluate_cells);
} else {
return null;
}
}),new cljs.core.Keyword(null,"reagent-render","reagent-render",-985383853),(function (){
return new cljs.core.PersistentVector(null, 7, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"div.cells","div.cells",104704865),new cljs.core.PersistentVector(null, 7, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"p","p",151049309),"Type an equation into the grid.",new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"br","br",934104792)], null),"it supports arithmetic, will support ranges soon",new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"br","br",934104792)], null),"Here's an example: ",new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"code","code",1586293142),"result = A1+A2+1"], null)], null),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"p","p",151049309),"Navigate with Tab, Shift+Tab, Enter, and UpArrow"], null),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"p","p",151049309),"You can paste csv into it too"], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"button","button",1456579943),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"on-click","on-click",1632826543),seven_guis.cells.copy_text.call(null,seven_guis.examples.example_csv_fibonacci)], null),"Copy Fibonacci CSV"], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"button","button",1456579943),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"on-click","on-click",1632826543),seven_guis.cells.copy_text.call(null,seven_guis.examples.example_csv_conv)], null),"Copy Convolution CSV"], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"table","table",-564943036),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"thead","thead",-291875296),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"tr","tr",-1424774646),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"th","th",-545608566)], null),(function (){var iter__4529__auto__ = (function seven_guis$cells$cells_$_iter__68363(s__68364){
return (new cljs.core.LazySeq(null,(function (){
var s__68364__$1 = s__68364;
while(true){
var temp__5735__auto__ = cljs.core.seq.call(null,s__68364__$1);
if(temp__5735__auto__){
var s__68364__$2 = temp__5735__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,s__68364__$2)){
var c__4527__auto__ = cljs.core.chunk_first.call(null,s__68364__$2);
var size__4528__auto__ = cljs.core.count.call(null,c__4527__auto__);
var b__68366 = cljs.core.chunk_buffer.call(null,size__4528__auto__);
if((function (){var i__68365 = (0);
while(true){
if((i__68365 < size__4528__auto__)){
var letter = cljs.core._nth.call(null,c__4527__auto__,i__68365);
cljs.core.chunk_append.call(null,b__68366,cljs.core.with_meta(new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"th","th",-545608566),letter], null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"key","key",-1516042587),letter], null)));

var G__68381 = (i__68365 + (1));
i__68365 = G__68381;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons.call(null,cljs.core.chunk.call(null,b__68366),seven_guis$cells$cells_$_iter__68363.call(null,cljs.core.chunk_rest.call(null,s__68364__$2)));
} else {
return cljs.core.chunk_cons.call(null,cljs.core.chunk.call(null,b__68366),null);
}
} else {
var letter = cljs.core.first.call(null,s__68364__$2);
return cljs.core.cons.call(null,cljs.core.with_meta(new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"th","th",-545608566),letter], null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"key","key",-1516042587),letter], null)),seven_guis$cells$cells_$_iter__68363.call(null,cljs.core.rest.call(null,s__68364__$2)));
}
} else {
return null;
}
break;
}
}),null,null));
});
return iter__4529__auto__.call(null,seven_guis.cells.letters);
})()], null)], null),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"tbody","tbody",-80678300),(function (){var map__68367 = cljs.core.deref.call(null,state);
var map__68367__$1 = (((((!((map__68367 == null))))?(((((map__68367.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__68367.cljs$core$ISeq$))))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__68367):map__68367);
var cells = cljs.core.get.call(null,map__68367__$1,new cljs.core.Keyword(null,"cells","cells",-985166822));
var editing = cljs.core.get.call(null,map__68367__$1,new cljs.core.Keyword(null,"editing","editing",1365491601));
var iter__4529__auto__ = (function seven_guis$cells$cells_$_iter__68369(s__68370){
return (new cljs.core.LazySeq(null,(function (){
var s__68370__$1 = s__68370;
while(true){
var temp__5735__auto__ = cljs.core.seq.call(null,s__68370__$1);
if(temp__5735__auto__){
var s__68370__$2 = temp__5735__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,s__68370__$2)){
var c__4527__auto__ = cljs.core.chunk_first.call(null,s__68370__$2);
var size__4528__auto__ = cljs.core.count.call(null,c__4527__auto__);
var b__68372 = cljs.core.chunk_buffer.call(null,size__4528__auto__);
if((function (){var i__68371 = (0);
while(true){
if((i__68371 < size__4528__auto__)){
var i = cljs.core._nth.call(null,c__4527__auto__,i__68371);
cljs.core.chunk_append.call(null,b__68372,cljs.core.with_meta(new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"tr","tr",-1424774646),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"td.row-header","td.row-header",-1570905346),i], null),(function (){var iter__4529__auto__ = ((function (i__68371,i,c__4527__auto__,size__4528__auto__,b__68372,s__68370__$2,temp__5735__auto__,map__68367,map__68367__$1,cells,editing,state){
return (function seven_guis$cells$cells_$_iter__68369_$_iter__68373(s__68374){
return (new cljs.core.LazySeq(null,((function (i__68371,i,c__4527__auto__,size__4528__auto__,b__68372,s__68370__$2,temp__5735__auto__,map__68367,map__68367__$1,cells,editing,state){
return (function (){
var s__68374__$1 = s__68374;
while(true){
var temp__5735__auto____$1 = cljs.core.seq.call(null,s__68374__$1);
if(temp__5735__auto____$1){
var s__68374__$2 = temp__5735__auto____$1;
if(cljs.core.chunked_seq_QMARK_.call(null,s__68374__$2)){
var c__4527__auto____$1 = cljs.core.chunk_first.call(null,s__68374__$2);
var size__4528__auto____$1 = cljs.core.count.call(null,c__4527__auto____$1);
var b__68376 = cljs.core.chunk_buffer.call(null,size__4528__auto____$1);
if((function (){var i__68375 = (0);
while(true){
if((i__68375 < size__4528__auto____$1)){
var j = cljs.core._nth.call(null,c__4527__auto____$1,i__68375);
cljs.core.chunk_append.call(null,b__68376,((cljs.core._EQ_.call(null,editing,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [i,j], null)))?cljs.core.with_meta(new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [seven_guis.cells.editing_cell,state,cells.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [i,j], null)),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [i,j], null)], null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"key","key",-1516042587),j], null)):cljs.core.with_meta(new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [seven_guis.cells.cell,state,cells.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [i,j], null)),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [i,j], null)], null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"key","key",-1516042587),j], null))));

var G__68382 = (i__68375 + (1));
i__68375 = G__68382;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons.call(null,cljs.core.chunk.call(null,b__68376),seven_guis$cells$cells_$_iter__68369_$_iter__68373.call(null,cljs.core.chunk_rest.call(null,s__68374__$2)));
} else {
return cljs.core.chunk_cons.call(null,cljs.core.chunk.call(null,b__68376),null);
}
} else {
var j = cljs.core.first.call(null,s__68374__$2);
return cljs.core.cons.call(null,((cljs.core._EQ_.call(null,editing,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [i,j], null)))?cljs.core.with_meta(new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [seven_guis.cells.editing_cell,state,cells.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [i,j], null)),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [i,j], null)], null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"key","key",-1516042587),j], null)):cljs.core.with_meta(new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [seven_guis.cells.cell,state,cells.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [i,j], null)),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [i,j], null)], null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"key","key",-1516042587),j], null))),seven_guis$cells$cells_$_iter__68369_$_iter__68373.call(null,cljs.core.rest.call(null,s__68374__$2)));
}
} else {
return null;
}
break;
}
});})(i__68371,i,c__4527__auto__,size__4528__auto__,b__68372,s__68370__$2,temp__5735__auto__,map__68367,map__68367__$1,cells,editing,state))
,null,null));
});})(i__68371,i,c__4527__auto__,size__4528__auto__,b__68372,s__68370__$2,temp__5735__auto__,map__68367,map__68367__$1,cells,editing,state))
;
return iter__4529__auto__.call(null,cljs.core.range.call(null,seven_guis.cells.cols));
})()], null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"key","key",-1516042587),i], null)));

var G__68383 = (i__68371 + (1));
i__68371 = G__68383;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons.call(null,cljs.core.chunk.call(null,b__68372),seven_guis$cells$cells_$_iter__68369.call(null,cljs.core.chunk_rest.call(null,s__68370__$2)));
} else {
return cljs.core.chunk_cons.call(null,cljs.core.chunk.call(null,b__68372),null);
}
} else {
var i = cljs.core.first.call(null,s__68370__$2);
return cljs.core.cons.call(null,cljs.core.with_meta(new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"tr","tr",-1424774646),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"td.row-header","td.row-header",-1570905346),i], null),(function (){var iter__4529__auto__ = ((function (i,s__68370__$2,temp__5735__auto__,map__68367,map__68367__$1,cells,editing,state){
return (function seven_guis$cells$cells_$_iter__68369_$_iter__68377(s__68378){
return (new cljs.core.LazySeq(null,(function (){
var s__68378__$1 = s__68378;
while(true){
var temp__5735__auto____$1 = cljs.core.seq.call(null,s__68378__$1);
if(temp__5735__auto____$1){
var s__68378__$2 = temp__5735__auto____$1;
if(cljs.core.chunked_seq_QMARK_.call(null,s__68378__$2)){
var c__4527__auto__ = cljs.core.chunk_first.call(null,s__68378__$2);
var size__4528__auto__ = cljs.core.count.call(null,c__4527__auto__);
var b__68380 = cljs.core.chunk_buffer.call(null,size__4528__auto__);
if((function (){var i__68379 = (0);
while(true){
if((i__68379 < size__4528__auto__)){
var j = cljs.core._nth.call(null,c__4527__auto__,i__68379);
cljs.core.chunk_append.call(null,b__68380,((cljs.core._EQ_.call(null,editing,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [i,j], null)))?cljs.core.with_meta(new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [seven_guis.cells.editing_cell,state,cells.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [i,j], null)),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [i,j], null)], null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"key","key",-1516042587),j], null)):cljs.core.with_meta(new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [seven_guis.cells.cell,state,cells.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [i,j], null)),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [i,j], null)], null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"key","key",-1516042587),j], null))));

var G__68384 = (i__68379 + (1));
i__68379 = G__68384;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons.call(null,cljs.core.chunk.call(null,b__68380),seven_guis$cells$cells_$_iter__68369_$_iter__68377.call(null,cljs.core.chunk_rest.call(null,s__68378__$2)));
} else {
return cljs.core.chunk_cons.call(null,cljs.core.chunk.call(null,b__68380),null);
}
} else {
var j = cljs.core.first.call(null,s__68378__$2);
return cljs.core.cons.call(null,((cljs.core._EQ_.call(null,editing,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [i,j], null)))?cljs.core.with_meta(new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [seven_guis.cells.editing_cell,state,cells.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [i,j], null)),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [i,j], null)], null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"key","key",-1516042587),j], null)):cljs.core.with_meta(new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [seven_guis.cells.cell,state,cells.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [i,j], null)),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [i,j], null)], null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"key","key",-1516042587),j], null))),seven_guis$cells$cells_$_iter__68369_$_iter__68377.call(null,cljs.core.rest.call(null,s__68378__$2)));
}
} else {
return null;
}
break;
}
}),null,null));
});})(i,s__68370__$2,temp__5735__auto__,map__68367,map__68367__$1,cells,editing,state))
;
return iter__4529__auto__.call(null,cljs.core.range.call(null,seven_guis.cells.cols));
})()], null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"key","key",-1516042587),i], null)),seven_guis$cells$cells_$_iter__68369.call(null,cljs.core.rest.call(null,s__68370__$2)));
}
} else {
return null;
}
break;
}
}),null,null));
});
return iter__4529__auto__.call(null,cljs.core.range.call(null,seven_guis.cells.rows));
})()], null)], null)], null);
})], null));
});

//# sourceMappingURL=cells.js.map?rel=1611092084162
