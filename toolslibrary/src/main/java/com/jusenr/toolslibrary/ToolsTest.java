/*
 * Copyright 2017 androidtools Jusenr
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jusenr.toolslibrary;

import com.jusenr.toolslibrary.utils.DateUtils;
import com.jusenr.toolslibrary.utils.JsonUtils;

/**
 * Description: AndroidTools test specific class.
 * Copyright  : Copyright (c) 2017
 * Email      : jusenr@163.com
 * Author     : Jusenr
 * Date       : 2017/08/25
 * Time       : 14:58
 * Project    ：AndroidTools.
 */
public class ToolsTest {

    public static void main(String[] args) {
        String s = "{\n" +
                "    \"data\": true,\n" +
                "    \"elapsed\": 25,\n" +
                "    \"http_code\": 200\n" +
                "}";
        boolean json = JsonUtils.isJson(s);
        System.out.println(json);

        long l = System.currentTimeMillis() / 1000L;
        System.out.println(l);
        System.out.println(DateUtils.timeCalculate(l));
        DateUtils.getCurrentTimeZone();
        DateUtils.getRawOffset();
        System.out.println(DateUtils.getWeekInMills(l));

        int i = 2 << 19;
        System.out.println(i);
    }
}