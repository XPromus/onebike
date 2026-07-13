<script lang="ts">
	import './layout.css';
	import favicon from '$lib/assets/favicon.svg';
	import NavBarButton from '$lib/components/NavBarButton.svelte';

	let { children } = $props();

	const pages = [
		{
			text: "Home",
			url: "/"
		}, {
			text: "Teams",
			url: "/teams",
		}, {
			text: "Riders",
			url: "/riders",
		}, {
			text: "Races",
			url: "/races",
		}, {
			text: "Cups",
			url: "/cups",
		}, {
			text: "Nations",
			url: "/nations",
		}
	]
</script>

<svelte:head><link rel="icon" href={favicon} /></svelte:head>
<div class="w-screen h-screen max-w-full max-h-full flex flex-row">
	<div class="flex flex-col basis-1/6 bg-mauve-100 space-y-5 py-5">
		<div class="flex flex-col">
			<span class="w-full text-center font-light text-2xl">
				OneBike
			</span>
			<span class="w-full text-center font-light text-xs">
				Admin Dashboard
			</span>
		</div>
		<div class="flex flex-col">
			{#each pages as page }
				<NavBarButton href={page.url}>
					{page.text}
				</NavBarButton>
			{/each}
		</div>
	</div>
	<div class="basis-5/6">
		{@render children()}	
	</div>
</div>
