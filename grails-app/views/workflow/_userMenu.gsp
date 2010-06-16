<h2>User</h2>
<ul>
   <li><a class="home" href="${createLink(uri: '/')}">Home</a></li>
  <g:ifLoggedIn>
   <li>Welcome, <g:link class="" controller="user" action="show" id="${user?.id}"><g:loggedInUser/></g:link></li>
   <li><g:link class="" controller="user" action="edit" id="${user?.id}">Edit profile</g:link></li>
   <li><g:link class="" controller="logout" action="index">Log out</g:link></li>
  </g:ifLoggedIn>
  <g:ifNotLoggedIn>
   <li><g:link class="" controller="login" action="auth">Log in</g:link></li>
  </g:ifNotLoggedIn>
   <li>More actions...</li>
</ul>
