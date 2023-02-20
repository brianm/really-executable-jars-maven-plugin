File jar = new File(new File(basedir, "target"), 'testapp-1.0.jar')
assert jar.exists()
result = jar.toString().execute().text
assert result.contains("Fake news!")
