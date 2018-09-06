<!doctype html>
<html>
<head>
    <title>Hello MVC</title>
</head>
<body>
<div>
    <form action="${pageContext.request.contextPath}/app/hello/configure" method="post">
        <input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}"/>
        <label for="locale">Select the language of your choice</label>
        <select id="locale" name="locale">
            <option value="nl-NL">Dutch</option>
            <option value="de-DE">German</option>
            <option value="fr-FR">French</option>
            <option value="en-UK">English (UK)</option>
            <option value="en-US">English (US)</option>
        </select>
        <button type="submit">Submit</button>
    </form>
</div>
</body>
</html>