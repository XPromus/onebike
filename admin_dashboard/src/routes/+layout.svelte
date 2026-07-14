<script lang="ts">
	import './layout.css';
	import favicon from '$lib/assets/favicon.svg';
	import NavBarButton from '$lib/components/NavBarButton.svelte';
    import Icon from "@iconify/svelte";

	let { children } = $props();

	const pages = [
		{
			text: "Home",
			icon: "",
			url: "/"
		}, {
			text: "Teams",
			icon: "",
			url: "/teams",
		}, {
			text: "Riders",
			icon: "",
			url: "/riders",
		}, {
			text: "Races",
			icon: "",
			url: "/races",
		}, {
			text: "Cups",
			icon: "",
			url: "/cups",
		}, {
			text: "Nations",
			icon: "",
			url: "/nations",
		}
	]
</script>

<svelte:head><link rel="icon" href={favicon} /></svelte:head>
<div class="flex flex-row w-screen max-w-full h-screen max-h-full">
	<div class="flex flex-col py-5 space-y-5 basis-1/6 bg-panel">
		<div class="flex flex-col">
			<span class="w-full text-2xl font-light text-center">
				OneBike
			</span>
			<span class="w-full text-xs font-light text-center">
				Admin Dashboard
			</span>
		</div>
		<div class="flex flex-col">
			{#each pages as page }
				<NavBarButton href={page.url}>
					<Icon icon={page.icon}/>
					{page.text}
				</NavBarButton>
			{/each}
		</div>
	</div>
	<div class="basis-5/6">
		{@render children()}	
	</div>
</div>
