<script lang="ts">
	import type { EntityContent } from "$lib/types/entity";
	import type { Snippet } from "svelte";

    let {
        class: className,
        children,
        onclick,
    }: {
        class?: string,
        children: Snippet,
        onclick?: (e: MouseEvent) => void, 
    } = $props();
</script>

<button
    onclick={onclick}
    class={`${className} flex flex-row rounded-md transition-colors duration-200 bg-panel hover:bg-panel-hover active:bg-panel-active hover:cursor-pointer`}
>
    {@render children()}
</button>
