<#import "/spring.ftl" as spring />

<html>
<head>
    <title>Events</title>
</head>
<body>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Start</th>
    </tr>
    </thead>

    <tbody>
    <#list events as event>
    <tr style="background: ${event.color}">
        <td>${event.id}</td>
        <td>${event.title}</td>
        <td>${event.start}</td>
    </tr>
    </#list>
    </tbody>
</table>
</body>
</html>