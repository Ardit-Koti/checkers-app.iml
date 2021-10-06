<head>
</head>
<body>
<#if currentUser??>
  <form action="/game" METHOD="get">
    <button type="button">Play</button>
  </form>
  <p>
      List of Players:
  </p>
  <#else>
  <p>

  </p>
</#if>
</body>