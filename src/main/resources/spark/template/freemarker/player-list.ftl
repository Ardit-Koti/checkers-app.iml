<head>
</head>
<body>
<#if currentUser??>
  <form action="/game" METHOD="get">
    <button type="button">Play</button>
  </form>
  <p>

  </p>
  <#else>
  <p>
    Number of Players: ${playernum}
  </p>
</#if>
</body>