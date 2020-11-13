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

public class CenterParser extends Markdown.MDParser {

    @Override
    public Markdown.MDWord parseLineFmt(String content) {
        return Markdown.MDWord.NULL;
    }

    @Override
    public Markdown.MDWord parseWordFmt(String content) {
        if (content.charAt(0) == '{' && content.charAt(content.length() - 1) == '}') {
            int length = content.length();
            return new Markdown.MDWord(content.substring(1, length - 1), length, Markdown.MD_FMT_CENTER);
        }
        return Markdown.MDWord.NULL;
    }
}
