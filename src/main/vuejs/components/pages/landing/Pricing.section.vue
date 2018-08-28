<template>
  <section class="section-full">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-lg-8">
          <div class="product-area-title text-center">
            <p class="text-uppercase">Creative People</p>
            <h2 class="h1">Team work builds trust and <br> trust builds growth</h2>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <div class="single-pricing-table">
            <div class="top">
              <div class="head text-center">
                <i class="fa fa-soccer-ball-o"/>
                <h5 class="text-white text-uppercase">Free</h5>
              </div>
              <div class="package text-center">
                <div class="price">€0</div>
                <span class="text-white">Per month</span>
              </div>
            </div>

            <div class="bottom text-center">
              <ul class="feature text-center list-unstyled">
                <li>Public apis only</li>
                <li>Secure Online Transfer</li>
                <li>Unlimited Styles</li>
                <li>Customer Service</li>
                <li>Manual Backup</li>
              </ul>
              <a
                href="#"
                class="primary-btn text-uppercase d-inline-flex align-items-center">Purchase<span class="lnr lnr-arrow-right"/></a>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="single-pricing-table">
            <div class="top">
              <div class="head text-center">
                <i class="fa fa-suitcase"/>
                <h5 class="text-white text-uppercase">Business</h5>
              </div>
              <div class="package text-center">
                <div class="price">€119</div>
                <span class="text-white">Per month</span>
              </div>
            </div>

            <div class="bottom text-center">
              <ul class="feature text-center list-unstyled">
                <li>2.5 GB Photos</li>
                <li>Secure Online Transfer</li>
                <li>Unlimited Styles</li>
                <li>Customer Service</li>
                <li>Manual Backup</li>
              </ul>
              <a
                href="#"
                class="primary-btn text-uppercase d-inline-flex align-items-center">Purchase<span class="lnr lnr-arrow-right"/></a>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="single-pricing-table">
            <div class="top">
              <div class="head text-center">
                <i class="fa fa-building"/>
                <h5 class="text-white text-uppercase">Ultimate</h5>
              </div>
              <div class="package text-center">
                <div class="price">€299</div>
                <span class="text-white">Per month</span>
              </div>
            </div>

            <div class="bottom text-center">
              <ul class="feature text-center list-unstyled">
                <li>2.5 GB Photos</li>
                <li>Secure Online Transfer</li>
                <li>Unlimited Styles</li>
                <li>Customer Service</li>
                <li>Manual Backup</li>
              </ul>
              <a
                href="#"
                class="primary-btn text-uppercase d-inline-flex align-items-center">Purchase<span class="lnr lnr-arrow-right"/></a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {

};
</script>

<style lang="scss">
.single-pricing-table {
  margin-top: 30px;

  .top {
    background-image: linear-gradient(0deg, #3c408f 0%, #5b61cf 100%);

    .head {
      padding: 35px 20px;

      i.fa {
        font-size: 48px;
        color: #00ff8c;
      }
    }

    .package {
      padding: 30px 20px;
      background: rgba(0, 0, 0, 0.1);

      .price {
        color: #00ff8c;
        font-size: 36px;
        margin-bottom: 10px;
        line-height: 1em;
      }
    }

    .head h5 {
      margin-top: 20px;
    }
  }

  .bottom {
    background: #f9f9ff;
    padding: 35px 20px 50px 20px;

    ul {
      margin-bottom: 50px;
      li {
        margin-top: 15px;
      }
    }
  }
}
</style>
