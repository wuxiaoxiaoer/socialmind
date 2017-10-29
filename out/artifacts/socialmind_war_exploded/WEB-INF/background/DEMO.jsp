<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html><html class=''>
<head><meta charset='UTF-8'>
<link rel='stylesheet' href='reset.css'>
<link rel='stylesheet' href='http://libs.useso.com/js/jqueryui/1.10.4/css/jquery-ui.min.css'>
<script src='prefixfree.min.js'></script>
<link rel='stylesheet prefetch' href='icons.css'>
<style class="cp-pen-styles">#container {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: 15px;
  font-family: 'Helvetica', Arial, sans-serif;
  font-size: 90%;
  line-height: normal;
  font-smoothing: antialiased;
}

#frame {
  position: relative;
  width: 100%;
  height: 100%;
}
#frame .frame {
  position: relative;
  overflow: hidden;
}
#frame .frame .inset .image {
  cursor: pointer;
  cursor: zoom-in;
  background-position: center;
  background-size: cover;
}
#frame .frame .inset .info .title {
  cursor: pointer;
  display: inline-block;
  font-weight: bold;
}
#frame .frame .inset .info .description {
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
#frame .frame .inset .info [class*='icon-'] {
  cursor: pointer;
  margin-left: 10px;
  display: inline-block;
  font-weight: bold;
}
#frame .frame .inset .info [class*='icon-']:before {
  margin-right: 5px;
  font-weight: normal;
}

.grid {
  display: flex;
  flex-flow: row wrap;
  color: #FFF;
}
.grid .frame {
  width: 250px;
  height: 250px;
  flex: 1 0 250px;
}
.grid .frame .inset {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: 10px;
}
.grid .frame .inset .image {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
}
.grid .frame .inset .info {
  position: absolute;
  left: 0;
  right: 0;
  bottom: 0;
  padding: 10px;
  background: rgba(0, 0, 0, 0.85);
  overflow: hidden;
}
.grid .frame .inset .info .shares {
  margin-top: 10px;
  display: block;
  font-size: 85%;
  text-align: right;
}

.list {
  color: #333;
}
.list .frame {
  height: 75px;
  line-height: 75px;
  box-shadow: inset 0 1px #EEE;
}
.list .frame:first-child {
  box-shadow: none;
}
.list .frame .inset .image {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 55px;
  height: 55px;
  margin: auto 10px;
}
.list .frame .inset .info {
  position: absolute;
  top: 0;
  left: 75px;
  bottom: 0;
}
.list .frame .inset .info .title {
  float: left;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.list .frame .inset .info .description {
  float: left;
  max-width: 40%;
  margin-left: 15px;
}
.list .frame .inset .info .shares {
  float: left;
  margin-left: 10px;
  font-size: 85%;
}
</style></head>

<body>
<main id='container'>
  <header id='header'>
    <span>View:</span>
    <button class='view-list'>List</button>
    <button class='view-grid'>Grid</button>
  </header>
  <ol class='grid' id='frame'>
    <li class='frame'>
      <div class='inset'>
        <div class='image'></div>
        <div class='info'>
          <div class='title'>Lorem Ipsum</div>
          <div class='description'></div>
          <div class='shares'>
            <div class='icon-lik likes'></div>
            <div class='icon-ask comments'></div>
          </div>
        </div>
      </div>
    </li>
    <li class='frame'>
      <div class='inset'>
        <div class='image'></div>
        <div class='info'>
          <div class='title'>Lorem Ipsum</div>
          <div class='description'></div>
          <div class='shares'>
            <div class='icon-lik likes'></div>
            <div class='icon-ask comments'></div>
          </div>
        </div>
      </div>
    </li>
    <li class='frame'>
      <div class='inset'>
        <div class='image'></div>
        <div class='info'>
          <div class='title'>Lorem Ipsum</div>
          <div class='description'></div>
          <div class='shares'>
            <div class='icon-lik likes'></div>
            <div class='icon-ask comments'></div>
          </div>
        </div>
      </div>
    </li>
    <li class='frame'>
      <div class='inset'>
        <div class='image'></div>
        <div class='info'>
          <div class='title'>Lorem Ipsum</div>
          <div class='description'></div>
          <div class='shares'>
            <div class='icon-lik likes'></div>
            <div class='icon-ask comments'></div>
          </div>
        </div>
      </div>
    </li>
    <li class='frame'>
      <div class='inset'>
        <div class='image'></div>
        <div class='info'>
          <div class='title'>Lorem Ipsum</div>
          <div class='description'></div>
          <div class='shares'>
            <div class='icon-lik likes'></div>
            <div class='icon-ask comments'></div>
          </div>
        </div>
      </div>
    </li>
    <li class='frame'>
      <div class='inset'>
        <div class='image'></div>
        <div class='info'>
          <div class='title'>Lorem Ipsum</div>
          <div class='description'></div>
          <div class='shares'>
            <div class='icon-lik likes'></div>
            <div class='icon-ask comments'></div>
          </div>
        </div>
      </div>
    </li>
    <li class='frame'>
      <div class='inset'>
        <div class='image'></div>
        <div class='info'>
          <div class='title'>Lorem Ipsum</div>
          <div class='description'></div>
          <div class='shares'>
            <div class='icon-lik likes'></div>
            <div class='icon-ask comments'></div>
          </div>
        </div>
      </div>
    </li>
    <li class='frame'>
      <div class='inset'>
        <div class='image'></div>
        <div class='info'>
          <div class='title'>Lorem Ipsum</div>
          <div class='description'></div>
          <div class='shares'>
            <div class='icon-lik likes'></div>
            <div class='icon-ask comments'></div>
          </div>
        </div>
      </div>
    </li>
    <li class='frame'>
      <div class='inset'>
        <div class='image'></div>
        <div class='info'>
          <div class='title'>Lorem Ipsum</div>
          <div class='description'></div>
          <div class='shares'>
            <div class='icon-lik likes'></div>
            <div class='icon-ask comments'></div>
          </div>
        </div>
      </div>
    </li>
    <li class='frame'>
      <div class='inset'>
        <div class='image'></div>
        <div class='info'>
          <div class='title'>Lorem Ipsum</div>
          <div class='description'></div>
          <div class='shares'>
            <div class='icon-lik likes'></div>
            <div class='icon-ask comments'></div>
          </div>
        </div>
      </div>
    </li>
    <li class='frame'>
      <div class='inset'>
        <div class='image'></div>
        <div class='info'>
          <div class='title'>Lorem Ipsum</div>
          <div class='description'></div>
          <div class='shares'>
            <div class='icon-lik likes'></div>
            <div class='icon-ask comments'></div>
          </div>
        </div>
      </div>
    </li>
    <li class='frame'>
      <div class='inset'>
        <div class='image'></div>
        <div class='info'>
          <div class='title'>Lorem Ipsum</div>
          <div class='description'></div>
          <div class='shares'>
            <div class='icon-lik likes'></div>
            <div class='icon-ask comments'></div>
          </div>
        </div>
      </div>
    </li>
    <li class='frame'>
      <div class='inset'>
        <div class='image'></div>
        <div class='info'>
          <div class='title'>Lorem Ipsum</div>
          <div class='description'></div>
          <div class='shares'>
            <div class='icon-lik likes'></div>
            <div class='icon-ask comments'></div>
          </div>
        </div>
      </div>
    </li>
    <li class='frame'>
      <div class='inset'>
        <div class='image'></div>
        <div class='info'>
          <div class='title'>Lorem Ipsum</div>
          <div class='description'></div>
          <div class='shares'>
            <div class='icon-lik likes'></div>
            <div class='icon-ask comments'></div>
          </div>
        </div>
      </div>
    </li>
    <li class='frame'>
      <div class='inset'>
        <div class='image'></div>
        <div class='info'>
          <div class='title'>Lorem Ipsum</div>
          <div class='description'></div>
          <div class='shares'>
            <div class='icon-lik likes'></div>
            <div class='icon-ask comments'></div>
          </div>
        </div>
      </div>
    </li>
  </ol>
</main>
<script src='http://libs.useso.com/js/jquery/1.11.0/jquery.min.js'>
</script><script src='http://libs.useso.com/js/jqueryui/1.10.4/jquery-ui.min.js'></script>
<script>$('#header button').on('click',function(){
  if ( $(this).hasClass('view-list') ) {
    $('#frame').removeClass('grid').addClass('list');
  } else if ( $(this).hasClass('view-grid') ) {
    $('#frame').removeClass('list').addClass('grid');
  }
});

$('.frame').each(function(){
  var images = ['1.jpg','2.jpg','3.jpg',
                '4.jpg','5.jpg','6.jpg'];
  $(this).find('.image')
  .css({ 'background-image': 'url('+images[Math.floor(Math.random()*images.length)]+')' });
  var like = 100,
      likes = Math.floor(Math.random() * like) + 1;
  $(this).find('.likes').text(likes);
  var comment = 50,
      comments = Math.floor(Math.random() * comment) + 1;
  $(this).find('.comments').text(comments);
});

$('.description').text('dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.');
</script>
</body></html>