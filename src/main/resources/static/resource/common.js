$('select[data-value]').each(function(index, item){
	
	//console.log(item);
	
	const items = $(item);
	// select 데이터를 객체화해서 items에 넣음
	
	const defaultValue = items.attr('data-value').trim();
	// 객체화된 items 안에서 data-value의 값을 defaultValue에 넣음
	
	if(defaultValue.length > 0){ // defaultValue 에 값이 있으면
		items.val(defaultValue); // items의 값을 defaultValue로 바꿔라
	}
})
// select 태그를 가지고오는데 값이 data-value인 애들만 가져와
// each는 가져온 데이터를 각각 실행시켜라. 만약, data-value값이 여러개면 여러번 실행