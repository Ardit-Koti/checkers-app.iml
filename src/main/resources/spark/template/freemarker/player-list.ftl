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
    Number of Players: ${playernum}
  </p>
</#if>
</body>