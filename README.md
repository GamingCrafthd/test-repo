# GitHub Markdown Tips

> **Note**<br/>
> This is a note!

> **Warning**<br/>
> This is a warning!

<details>
<summary>This is a thing</summary>
Look at me, mum!
You can't look at me!
</details>

# Bash commands

**Create a hyperlink in bash**
```bash
$ echo -e '\e]8;;URL_HERE\aTEXT_HERE\e]8;;\a'
```

**Search in every jar in a folder for a file**
```bash
$ for i in *.jar; do grep -iq "SEARCH_QUERY" < <( unzip -l $i ) && echo $i; done
```

**Get all URLs in a file**
```bash
$ cat FILE | grep -Eo "(http|https)://[a-zA-Z0-9./?=_%:&-]*" | sort -u
```

**Run a command inside a screen with custom title**
```bash
$ screen -S "TITLE" -d -m COMMAND
```

**Test the Apache config for errors**
```bash
$ sudo apachectl configtest
```
