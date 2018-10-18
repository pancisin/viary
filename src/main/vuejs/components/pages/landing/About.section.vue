<template>
  <section class="section-full studio-area">
    <div class="container">
      <div class="row">
        <div class="col-lg-6">
          <div class="studio-content">
            <p class="text-uppercase c-white">
              Know Thyself
            </p>
            <h2 class="h1 c-white text-uppercase mb-20">
              Why not try Viary ?
            </h2>
            <p class="c-white mB-30">
              Writing diary or daily journal seems to be simple and worthless at some point. 
              But you will be surprised how effectivelly it can improve your time management 
              and overall self-awareness. Simple day notes can provide you option to keep track
              of important decisions to not forget and see your progress in time. Reading
              throught notes in past you can remember who you were. And based on occasional
              walkthrough past thoughts may enhance reflection, organizational skills and even may
              give more meaning to your life.

              <!-- By remembering who you were yesterday, you can avoid making the same mistake today. -->
            </p>
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
.studio-area {
  position: relative;

  &:before {
    position: absolute;
    content: "";
    left: 0;
    top: 0;
    width: 50%;
    height: 100%;
    background: #404598;
    z-index: 2;
  }

  &:after {
    position: absolute;
    content: "";
    right: 0;
    top: 0;
    width: 50%;
    height: 100%;
    background: url(https://colorlib.com/etc/datarc/img/studio-bg.jpg) no-repeat center center/cover;
    z-index: 1;
  }

  .container {
    position: relative;
    z-index: 3;
  }
}

@media (max-width: 991px) {
  .studio-area:before {
    background: rgba(64, 69, 152, 0.95);
    width: 100%;
  }
}
</style>
