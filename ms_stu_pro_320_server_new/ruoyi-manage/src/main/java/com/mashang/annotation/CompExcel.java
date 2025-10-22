package com.mashang.annotation;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ExcelIgnoreUnannotated  //没有标注@ExcelProperty的字段不导出
@ColumnWidth(16)  //设置列宽度
@HeadRowHeight(14)  //设置表头行高
@HeadFontStyle(fontHeightInPoints = 11) //设置表头字体样式
public @interface CompExcel {
}