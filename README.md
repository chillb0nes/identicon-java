[![❤️](https://forthebadge.com/images/badges/built-with-love.svg)](https://forthebadge.com)

# Identicon

Command-line java application to generate Github-style identicons.

```
Usage: [java -jar] identicon[.jar] [OPTIONS] <input>

Generate identicon of specified size by hashing user input string with
specified message digest algorithm and show preview or save generated
image to file directly.

Options:
 -a (--algorithm) NAME : Message digest algorithm. (default: SHA-256)
 -b (--border)         : Draw empty border around identicon. (default: true)
 -g (--no-gui)         : Do not show GUI. (default: false)
 -i (--image-size) N   : Result image size(px). (default: 250)
 -n (--iterations) N   : Number of hashing iterations. (default: 1)
 -o (--output) FILE    : Output file.
 -s (--size) N         : Identicon size(0-8). (default: 5)
```

## Examples

![1](https://raw.githubusercontent.com/Stingray42/identicon-java/master/images/1.png)
![2](https://raw.githubusercontent.com/Stingray42/identicon-java/master/images/2.png)
![3](https://raw.githubusercontent.com/Stingray42/identicon-java/master/images/3.png)

## Credits

* [Args4j](https://github.com/kohsuke/args4j)
