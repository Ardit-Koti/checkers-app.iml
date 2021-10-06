<head>
</head>
<body>
<#if currentUser??>
  <form action="/game" METHOD="get">
    <button type="button">Play</button>
  </form>
  <#else>
  <p>
    Number of players: ${playernum}
  </p>
</#if>
</body>