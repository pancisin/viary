<template>
  <div class="sidebar">
    <div class="sidebar-inner">
      <!-- ### $Sidebar Header ### -->
      <div class="sidebar-logo">
        <div class="peers ai-c fxw-nw">
          <div class="peer peer-greed">
            <a
              class="sidebar-link td-n"
              href="index.html">
              <div class="peers ai-c fxw-nw">
                <div class="peer">
                  <div class="logo">
                    <img
                      src="assets/static/images/logo.png"
                      alt="">
                  </div>
                </div>
                <div class="peer peer-greed">
                  <h5 class="lh-1 mB-0 logo-text">ApiGo</h5>
                </div>
              </div>
            </a>
          </div>
          <div class="peer">
            <div class="mobile-toggle sidebar-toggle">
              <a
                href=""
                class="td-n">
                <i class="fa fa-arrow-left"/>
              </a>
            </div>
          </div>
        </div>
      </div>

      <ul class="sidebar-menu scrollable pos-r">
        <li class="nav-item mT-30">
          <router-link :to="{ name: 'home' }">
            <span class="icon-holder">
              <i class="c-light-blue-500 fa fa-home"/>
            </span>
            <span class="title">Dashboard</span>
          </router-link>
        </li>
        <li class="nav-item">
          <router-link :to="{ name: 'test' }">
            <span class="icon-holder">
              <i class="c-light-blue-500 fa fa-book"/>
            </span>
            <span class="title">Test</span>
          </router-link>
        </li>

        <li class="nav-item dropdown">
          <a
            class="dropdown-toggle"
            href="javascript:void(0);">
            <span class="icon-holder">
              <i class="c-red-500 fa fa-file-o"/>
            </span>
            <span class="title">Pages</span>
            <span class="arrow">
              <i class="ti-angle-right"/>
            </span>
          </a>
          <ul class="dropdown-menu">
            <li>
              <a
                class="sidebar-link"
                href="blank.html">Blank</a>
            </li>
            <li>
              <a
                class="sidebar-link"
                href="404.html">404</a>
            </li>
            <li>
              <a
                class="sidebar-link"
                href="500.html">500</a>
            </li>
            <li>
              <a
                class="sidebar-link"
                href="signin.html">Sign In</a>
            </li>
            <li>
              <a
                class="sidebar-link"
                href="signup.html">Sign Up</a>
            </li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
</style>
