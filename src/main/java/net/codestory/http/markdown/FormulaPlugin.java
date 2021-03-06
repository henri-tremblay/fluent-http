/**
 * Copyright (C) 2013-2014 all@code-story.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package net.codestory.http.markdown;

import java.io.*;
import java.net.*;
import java.util.*;

import org.markdown4j.*;

class FormulaPlugin extends Plugin {
  public FormulaPlugin() {
    super("formula");
  }

  @Override
  public void emit(StringBuilder result, List<String> lines, Map<String, String> parameters) {
    String type = parameters.getOrDefault("type", "png");

    lines.forEach(line -> {
      if (!line.trim().isEmpty()) {
        result.append("<img src=\"http://latex.codecogs.com/")
          .append(type)
          .append(".download?")
          .append(encode(line))
          .append("\" />");
      }
    });
  }

  static String encode(String line) {
    try {
      return URLEncoder.encode(line, "US-ASCII").replace("+", "%20");
    } catch (UnsupportedEncodingException e) {
      return line;
    }
  }
}
