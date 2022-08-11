<template>
	<video :class="{smallVideo:isAnswer}" autoplay/>

	
</template>

<script>
export default {
	name: 'OvVideo',

	props: {
		streamManager: Object,
		isAnswer:Boolean
	},
	watch:{
		isAnswer: function(data){
			console.log(data);
		}
	},
	mounted () {
		this.streamManager.addVideoElement(this.$el);
	},
};
</script>

<style scoped>
.smallVideo{
	background-color: white;
	width: 344px;
	height: 400px;
	margin: 8px auto 4px auto;
	border: solid #FFD39B 1px;
	border-radius: 5px;
}
video{
	background-color: white;
	width: 344px;
	height: 560px;
	margin: 8px auto 4px auto;
	border: solid #FFD39B 1px;
	border-radius: 5px;
}
</style>