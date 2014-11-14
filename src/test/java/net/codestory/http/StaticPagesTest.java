/**
 * Copyright (C) 2013 all@code-story.net
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
package net.codestory.http;

import net.codestory.http.testhelpers.*;

import org.junit.*;

public class StaticPagesTest extends AbstractProdWebServerTest {
  @Test
  public void webJars() {
    get("/webjars/bootstrap/3.2.0/css/bootstrap.min.css").produces(200, "text/css", "Bootstrap v3.2.0");
    get("/webjars/bootstrap/3.2.0/js/bootstrap.min.js").produces(200, "application/javascript", "Bootstrap v3.2.0");
    get("/webjars/").produces(404);
  }

  @Test
  public void html() {
    get("/").produces("text/html", "Hello From a File");
    get("/index.html").produces("text/html", "Hello From a File");
    get("/test").produces("text/html", "TEST");
    get("/test.html").produces("text/html", "TEST");
  }

  @Test
  public void javascript() {
    get("/js/script.js").produces("application/javascript", "console.log('Hello');");
  }

  @Test
  public void coffeescript() {
    get("/js/script.coffee").produces("application/javascript", "console.log('Hello');");
    get("/js/script.js").produces("application/javascript", "console.log('Hello');");
    get("/js/anotherscript.js").produces("application/javascript", "console.log('foobar');");
    get("/js/non-existing.js").produces(404);
    get("/js/non-existing.coffee").produces(404);
  }

  @Test
  public void literate_coffees() {
    get("/js/literate.js").produces("application/javascript", "console.log('Hello');");
  }

  @Test
  public void css() {
    get("/assets/style.css").produces("text/css", "* {}");
  }

  @Test
  public void less() {
    get("/assets/style.less").produces("text/css", "body h1 {\n  color: red;\n}");
    get("/assets/anotherstyle.css").produces("text/css", "body h1 {\n  color: red;\n}");
    get("/assets/non-existing.css").produces(404);
    get("/assets/non-existing.less").produces(404);
  }

  @Test
  public void markdown() {
    get("/hello.md").produces("text/html", "<strong>Hello</strong>");
    get("/goodbye.markdown").produces("text/html", "<strong>Good Bye</strong>");
  }

  @Test
  public void private_files() {
    get("/../private.txt").produces(404);
    get("/_config.yaml").produces(404);
    get("/_layouts/layout.html").produces(404);
    get("/unknown").produces(404);
    get("/js").produces(404);
  }
}
