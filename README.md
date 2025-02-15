# Axur Code Test

- A Java code capable of finding the deepest element inside a HTML tree.
- The input is a URL and the output is the terminal.
- The code can deal with malformed HTML, broken URLs (These are ignored), and connection errors. A error message is returned.
- Extra: Print back the HTML tree, from the tree of objects.

### Validated Cases:
<details>
    <summary>Default</summary>
    <br>
<code>
    
    java HtmlAnalyzer http://hiring.axreng.com/internship/example1.html
    
</code>
    <p>Output: "Este é o título."</p>
</details>

<details>
    <summary>Basic</summary>
    </br>
<code>
    
    java HtmlAnalyzer --test "<html>
        <head>
            <title>
                Este é o título.
            </title>
        </head>
        <body>
            Este é o corpo.
        </body>
    </html>"

</code>
    <p>Output: "Este é o título."</p>
</details>

<details>
    <summary>With spaces</summary>
    </br>
<code>
    
    java HtmlAnalyzer --test "<html>
        <head>
        
            <title>
                Este é o título.

            </title>
        </head>

        <body>
        
            Este é o corpo.
        </body>
    </html>

    "

</code>
    <p>Output: "Este é o título."</p>
</details>

<details>
    <summary>Without spaces</summary>
    </br>
<code>

    java HtmlAnalyzer --test "<html>
    <head>
    <title>
    Este é o título.
    </title>
    </head>
    <body>
    Este é o corpo.
    </body>
    </html>"

</code>
    <p>Output: "Este é o título."</p>
</details>

<details>
    <summary><title> without closure</summary>
    </br>
<code>
    
    java HtmlAnalyzer --test "<html>
        <head>
            <title>
                Este é o título.
        </head>
        <body>
            Este é o corpo.
        </body>
    </html>"

</code>
    <p>Output: "malformed HTML"</p>
</details>

<details>
    <summary><title> with </blob></summary>
    </br>
<code>
    
    java HtmlAnalyzer --test "<html>
        <head>
            <title>
                Este é o título.
            </blob>
        </head>
        <body>
            Este é o corpo.
        </body>
    </html>"

</code>
    <p>Output: "malformed HTML"</p>
</details>

<details>
    <summary><body> with </a></summary>
    </br>
<code>

    java HtmlAnalyzer --test "<html>
        <head>
            <title>
                Este é o título.
            </title>
        </head>
        <body>
            Este é o corpo.
        </a>
    </html>"

</code>
    <p>Output: "malformed HTML"</p>
</details>

<details>
    <summary><title> with <title></summary>
    </br>
<code>
    
    java HtmlAnalyzer --test "<html>
        <head>
            <title>
                Este é o título.
            <title>
        </head>
        <body>
            Este é o corpo.
        </body>
    </html>"

</code>
    <p>Output: "malformed HTML"</p>
</details>

<details>
    <summary><title> with </head>, and </title> after</summary>
    </br>
<code>
    
    java HtmlAnalyzer --test "<html>
        <head>
            <title>
                Este é o título.
        </head>
            </title>
        <body>
            Este é o corpo.
        </body>
    </html>"

</code>
    <p>Output: "malformed HTML"</p>
</details>

<details>
    <summary>Text with <</summary>
    </br>
<code>
    
    java HtmlAnalyzer --test "<html>
        <head>
            <title>
                Este é o título.
            </title>
        </head>
        <body>
            <Este é o corpo.
        </body>
    </html>"

</code>
    <p>Output: "malformed HTML"</p>
</details>

<details>
    <summary>No connection</summary>
    </br>
<code>

    java HtmlAnalyzer http://hiring.axreng.com/internship/NULL.html

</code>
    <p>Output: "URL connection error"</p>
</details>

<details>
    <summary>Two levels deep</summary>
    </br>
<code>

    java HtmlAnalyzer --test "<html>
        <head>
            <title>
                Este é o título.
            </title>
        </head>
        <body>
            Este é o corpo.
            <div>
                Este é um bloco.
            </div>
        </body>
    </html>"

</code>
    <p>Output: "Este é o título."</p>
</details>

<details>
    <summary>Second case</summary>
    </br>
<code>

    java HtmlAnalyzer --test "<html>
        <head>
            <title>
                Este é o título.
            </title>
        </head>
        <body>
            Este é o corpo.
            <div>
                Este é um bloco.
                <div>
                    Este é um bloco 2.
                </div>
            </div>
        </body>
    </html>"

</code>
    <p>Output: "Este é um bloco 2."</p>
</details>

<details>
    <summary>Deeper case</summary>
    </br>
<code>
    
    java HtmlAnalyzer --test "<html>
        <head>
            <title>
                Este é o título.
            </title>
        </head>
        <body>
            Este é o corpo.
            <div>
                Este é um bloco 1.
            </div>
            <div>
                Este é um bloco 2.
                <div>
                    Este é um bloco 2.1.
                </div>
            </div>
        </body>
    </html>"

</code>
    <p>Output: "Este é um bloco 2.1."</p>
</details>

<details>
    <summary>Two equal levels</summary>
    </br>
<code>
    
    java HtmlAnalyzer --test "<html>
        <head>
            <title>
                Este é o título.
            </title>
        </head>
        <body>
            Este é o corpo.
            <div>
                Este é um bloco 1.
                <div>
                    Este é um bloco 1.1.
                </div>
            </div>
            <div>
                Este é um bloco 2.
                <div>
                    Este é um bloco 2.1.
                </div>
            </div>
        </body>
    </html>"

</code>
    <p>Output: "Este é um bloco 1.1."</p>
</details>

<details>
    <summary>Even deeper case</summary>
    </br>
<code>
    
    java HtmlAnalyzer --test "<html>
        <head>
            <title>
                Este é o título.
            </title>
        </head>
        <body>
            Este é o corpo.
            <div>
                Este é um bloco 1.
                <div>
                    Este é um bloco 1.1.
                </div>
            </div>
            <div>
                Este é um bloco 2.
                <div>
                    Este é um bloco 2.1.
                </div>
            </div>
            <div>
                Este é um bloco 3.
                <div>
                    Este é um bloco 3.1.
                    <div>
                        Este é um bloco 3.2.
                    </div>
                </div>
            </div>
        </body>
    </html>"

</code>
    <p>Output: "Este é um bloco 3.2."</p>
</details>
</br>

...(I spent way too much energy writing these).