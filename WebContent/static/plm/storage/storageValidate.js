/**仓库管理验证
 * 东起凯
 * 2018-08-15
 */

jQuery.validator.addMethod("order_num",function(value, element) {
	return this.optional(element) || /^[a-zA-Z0-9]*$/.test(value);
},"请输入字母数字");

jQuery.validator.addMethod("amount",function(value, element) {
	return this.optional(element) || /^(([0-9]|([1-9][0-9]{0,12}))((\.[0-9]{1,2})?))$/.test(value);
},"请输入正确金额");

jQuery.validator.addMethod("special_str",function(value, element) {
	return this.optional(element) || /^[A-Za-z0-9\u4e00-\u9fa5\-\.]+$/.test(value);
},"含有特殊字符");

jQuery.validator.addMethod("num",function(value, element) {
	return this.optional(element) || /[^\d]/g.test(value);
},"只允许输入数字");

jQuery.validator.addMethod("chinese",function(value, element) {
	return this.optional(element) || /^[\u4e00-\u9fa5]/g.test(value);
},"请输入汉字单位");

jQuery.validator.addMethod("decimal",function(value, element) {
	return this.optional(element) || /^\d{0,8}\.{0,1}(\d{1,8})?$/.test(value);
},"请输入正确的小数");

jQuery.validator.addMethod("bank",function(value, element) {
	return this.optional(element) || /^([1-9]{1})(\d{14}|\d{18})$/.test(value);
},"请输入正确的银行账号");

jQuery.validator.addMethod("lrunlv", function(value, element) {       
    return this.optional(element) || /^[-]{0,1}\d{1,3}(\.\d{1,6})?$/.test(value);       
}, "整数位不超过三位,小数位不能超过六位"); 

