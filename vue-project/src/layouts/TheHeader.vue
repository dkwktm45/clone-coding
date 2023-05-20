<template>
	<div>
		<header>
			<nav class="navbar navbar-expand-sm navbar-dark bg-primary">
				<div class="container-fluid">
					<RouterLink class="navbar-brand" to="/">GYM CODING</RouterLink>
					<button
						class="navbar-toggler"
						type="button"
						data-bs-toggle="collapse"
						data-bs-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent"
						aria-expanded="false"
						aria-label="Toggle navigation"
					>
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav me-auto">
							<li class="nav-item">
								<router-link class="nav-link" active-class="active" to="/">
									HOME
								</router-link>
							</li>
							<li class="nav-item">
								<router-link class="nav-link" active-class="active" to="/about">
									ABOUT
								</router-link>
							</li>
							<li class="nav-item">
								<router-link class="nav-link" active-class="active" to="/posts">
									게시글
								</router-link>
							</li>
							<li class="nav-item">
								<router-link
									class="nav-link"
									active-class="active"
									to="/nested"
								>
									Nested
								</router-link>
							</li>
							<li class="nav-item">
								<router-link class="nav-link" active-class="active" to="/my">
									MyPage
								</router-link>
							</li>
						</ul>
						<form class="d-flex" role="search">
							<button
								class="btn btn-outline-success"
								type="button"
								@click="goPage"
							>
								글쓰기
							</button>
						</form>
					</div>
				</div>
			</nav>
		</header>
	</div>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();
const goPage = () => {
	router.push({
		name: 'PostCreateView',
	});
};
</script>

<style></style>
