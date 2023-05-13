<template>
	<AppCard>
		<div class="card-body">
			<h5 class="card-title">{{ title }}</h5>
			<p class="card-text">
				{{ content }}
			</p>
			<p class="text-muted">{{ createdAt }}</p>
		</div>
	</AppCard>
</template>

<script setup>
import AppCard from '@/components/AppCard.vue';

defineProps({
	title: {
		type: String,
		required: true,
	},
	content: {
		type: [String, Date, Number],
	},
	createdAt: {
		type: [String, Date, Number],
	},
});
</script>

<style lang="scss" scoped></style>
