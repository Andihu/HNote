/*
 *  Copyright (C) 2015, Jhuster, All Rights Reserved
 *
 *  Author:  Jhuster(lujun.hust@gmail.com)
 *  
 *  https://github.com/Jhuster/JNote
 *  
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; version 2 of the License.
 */
package com.hdemo.hnote.markdown.parser;


import com.hdemo.hnote.markdown.Markdown;

public class OrderListParser extends Markdown.MDParser {

    private static final String KEY = "^[0-9].*";

    @Override
    public Markdown.MDWord parseLineFmt(String content) {
        if (!content.matches(KEY)) {
            return Markdown.MDWord.NULL;
        }
        return new Markdown.MDWord("", 0, Markdown.MD_FMT_ORDER_LIST);
    }

    @Override
    public Markdown.MDWord parseWordFmt(String content) {
        return Markdown.MDWord.NULL;
    }

}
