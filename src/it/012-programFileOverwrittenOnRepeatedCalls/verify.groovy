File bananaJar = new File(new File(basedir, "target"), 'bananas')
assert bananaJar.exists()
result = bananaJar.toString().execute().text
assert result.contains("Cheerio world!")
