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

public class ItalicParser extends Markdown.MDParser {

    private static final String KEY = "*";

    @Override
    public Markdown.MDWord parseLineFmt(String content) {
        return Markdown.MDWord.NULL;
    }

    @Override
    public Markdown.MDWord parseWordFmt(String content) {
        if (!content.startsWith(KEY)) {
            return Markdown.MDWord.NULL;
        }
        int position = content.indexOf(KEY, 1);
        if (position == -1) {
            return Markdown.MDWord.NULL;
        }
        return new Markdown.MDWord(content.substring(1, position), position + 1, Markdown.MD_FMT_ITALIC);
    }
}
