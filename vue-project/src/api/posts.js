//axios
const posts = [
	{ id: 1, title: '제목1', createdAt: '2022-10-11' },
	{ id: 2, title: '제목2', createdAt: '2022-10-12' },
	{ id: 3, title: '제목3', createdAt: '2022-10-13' },
	{ id: 4, title: '제목4', createdAt: '2022-10-14' },
	{ id: 5, title: '제목5', createdAt: '2022-10-15' },
];

export function getPosts() {
	return posts;
}

export function getPostById(id) {
	return posts.find(item => item.id === id);
}
