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
package net.codestory.http.compilers;

import static java.nio.charset.StandardCharsets.*;

import java.io.*;
import java.nio.file.*;

public interface CacheEntry extends Serializable {
  String content();

  byte[] toBytes();

  String sha1();

  public static CacheEntry fromFile(String sha1, File file) throws IOException {
    byte[] data = Files.readAllBytes(file.toPath());

    return new CacheEntry() {
      @Override
      public String content() {
        return new String(data, UTF_8);
      }

      @Override
      public byte[] toBytes() {
        return data;
      }

      @Override
      public String sha1() {
        return sha1;
      }
    };
  }

  public static CacheEntry fromString(String sha1, String content) {
    return new CacheEntry() {
      @Override
      public String content() {
        return content;
      }

      @Override
      public byte[] toBytes() {
        return content.getBytes(UTF_8);
      }

      @Override
      public String sha1() {
        return sha1;
      }
    };
  }

  public static CacheEntry noCache(String content) {
    return new CacheEntry() {
      @Override
      public String content() {
        return content;
      }

      @Override
      public byte[] toBytes() {
        return content.getBytes(UTF_8);
      }

      @Override
      public String sha1() {
        return "";
      }
    };
  }
}
